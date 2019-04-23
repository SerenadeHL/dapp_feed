package com.dong.dapp.utils

import android.annotation.SuppressLint
import com.dong.dapp.bean.kyc.FinishKYCBean
import com.dong.dapp.bean.kyc.KYCBizTokenBean
import com.dong.dapp.bean.kyc.KYCInfoBean
import com.dong.dapp.bean.kyc.UserDetectResultBean
import com.dong.dapp.exception.BaseException
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.DAppRequest
import com.megvii.faceidiol.sdk.manager.IDCardManager
import com.megvii.faceidiol.sdk.manager.IDCardResult
import com.megvii.faceidiol.sdk.manager.UserDetectConfig
import com.megvii.meglive_sdk.sdk.listener.FaceIdDetectListener
import com.megvii.meglive_sdk.sdk.listener.FaceIdInitListener
import com.megvii.meglive_sdk.sdk.manager.FaceIdManager
import io.reactivex.Observable
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.utils.app.AppManager

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-17 10:30:37
 */

object KYCUtils {
    private const val BIZ_TOKEN_IS_NULL = 0
    private const val ID_CARD_MANAGER_INIT_FAILED = 1
    private const val ID_CARD_CANT_BE_DETECTED = 2
    private const val ID_CARD_HAS_BEEN_USED = 3
    private const val KYC_HAVE_BEEN_FINISHED = 4

    @SuppressLint("CheckResult")
    fun start() {
        var idCardResult: IDCardResult? = null
        var bizToken: String? = null
        var frontImage: String? = null
        var backImage: String? = null
        var sign: String? = null
        DAppRequest
            .getKYCSign()//获取签名信息/
            .flatMap { data ->
                //初始化身份证识别
                sign = data.sign
                return@flatMap initIdCardDetect(sign)
            }
            .flatMap { token ->
                //检测身份证
                return@flatMap detectIdCard(token)
            }
            .flatMap { result ->
                idCardResult = result
                //验证身份证是否可用
                return@flatMap DAppRequest.isIdCardNumberAvailable(result.idCardInfo.idcardNumber.text)
            }
            .flatMap { data ->
                //获取人脸识别token并且异步上传身份证图片
                return@flatMap when (data.isRight) {
                    1 -> {
                        //异步上传身份证图片
                        UploadFileUtils.upload(idCardResult!!.idCardInfo.imageFrontside) {
                            frontImage = it
                        }
                        UploadFileUtils.upload(idCardResult!!.idCardInfo.imageBackside) {
                            backImage = it
                        }
                        DAppRequest.getKYCBizToken(
                            sign ?: "",
                            idCardResult?.idCardInfo?.name?.text ?: "",
                            idCardResult?.idCardInfo?.idcardNumber?.text ?: ""
                        )
                    }
                    0 -> Observable.error<BaseException>(
                        BaseException(
                            ID_CARD_HAS_BEEN_USED,
                            "this id card has been used"
                        )
                    )
                    else -> Observable.error<BaseException>(
                        BaseException(
                            KYC_HAVE_BEEN_FINISHED,
                            "you have finished kyc"
                        )
                    )
                }
            }
            .map {
                //转换为KYCBizTokenBean类型便于操作
                return@map it as KYCBizTokenBean?
            }
            .flatMap { kycBizTokenBean ->
                //初始化人脸识别
                bizToken = kycBizTokenBean.bizToken
                return@flatMap initUserDetect(bizToken)
            }
            .flatMap {
                //进行人脸识别
                return@flatMap detectUser()
            }
            .flatMap {
                //向后台提交KYC信息
                return@flatMap DAppRequest.finishKYC(getUploadInfo(bizToken, idCardResult, frontImage, backImage))
            }
            .subscribe(object : BaseObserver<FinishKYCBean?>() {
                override fun next(data: FinishKYCBean?) {
                    "KYC验证成功".log()
                }

                override fun error(error: Throwable) {
                    super.error(error)
                    "KYC验证失败".log()
                    if (error is BaseException) {
                        "BaseExceptionErrorCode=${error.code}".log()
                        "BaseExceptionErrorMsg=${error.msg}".log()
                        when (error.code) {
                            ID_CARD_HAS_BEEN_USED -> {
                                //TODO 测试
                                AppManager.instance.currentActivity.toast("身份证已经被使用")
                            }
                            KYC_HAVE_BEEN_FINISHED -> {
                                //TODO 测试
                                AppManager.instance.currentActivity.toast("已经完成KYC")
                            }
                        }
                    }
                }
            })
    }

    /**
     * 获取KYC提交到后台的信息
     */
    private fun getUploadInfo(
        bizToken: String?,
        idCardResult: IDCardResult?,
        frontImage: String?,
        backImage: String?
    ): KYCInfoBean {
        val idCardInfo = idCardResult?.idCardInfo
        val birthday =
            "${idCardInfo?.birthYear?.text}-${idCardInfo?.birthMonth?.text}-${idCardInfo?.birthDay?.text}"
        return KYCInfoBean(
            bizToken,
            KYCInfoBean.KYCUserInfoBean(
                idCardInfo?.name?.text,
                idCardInfo?.idcardNumber?.text,
                frontImage,
                backImage,
                birthday,
                KYCInfoBean.InfoBean(
                    idCardInfo?.name?.text,
                    idCardInfo?.gender?.text,
                    idCardInfo?.nationality?.text,
                    idCardInfo?.birthYear?.text,
                    idCardInfo?.birthMonth?.text,
                    idCardInfo?.birthDay?.text,
                    idCardInfo?.idcardNumber?.text,
                    idCardInfo?.address?.text,
                    idCardInfo?.issuedBy?.text,
                    idCardInfo?.validDateStart?.text,
                    idCardInfo?.validDateEnd?.text,
                    idCardInfo?.frontsideCardRect,
                    idCardInfo?.frontsideCompleteness,
                    idCardInfo?.frontsideLegality,
                    idCardInfo?.backsideCardRect,
                    idCardInfo?.backsideCompleteness,
                    idCardInfo?.backsideLegality
                )
            )
        )
    }

    /**
     * 初始化身份证识别
     * @param sign 签名
     */
    private fun initIdCardDetect(sign: String?): Observable<String?> {
        return Observable.create {
            val applicationContext = AppManager.instance.currentActivity.applicationContext
            val config = UserDetectConfig()
            //设置屏幕方向0竖屏 1横屏
            config.screenDirection = 0
            //设置检测身份证哪一面，0双面 1人像面 2国徽面
            config.captureImage = 0
            IDCardManager.getInstance()
                .init(applicationContext, sign, "hmac_sha1", config, object : IDCardManager.InitCallBack {
                    override fun initSuccess(bizToken: String?) {
                        try {
                            "身份证识别初始化成功,bizToken=$bizToken".log()
                            it.onNext(bizToken!!)
                            it.onComplete()
                        } catch (e: Exception) {
                            it.onError(BaseException(BIZ_TOKEN_IS_NULL, "bizToken为空"))
                        }
                    }

                    override fun initFailed(resultCode: Int, resultMessage: String?) {
                        "身份证识别初始化失败,resultCode=$resultCode,resultMessage=$resultMessage".log()
                        it.onError(
                            BaseException(
                                ID_CARD_MANAGER_INIT_FAILED,
                                "身份证识别初始化失败,resultCode=$resultCode,resultMessage=$resultMessage"
                            )
                        )
                    }
                })
        }
    }

    /**
     * 初始化人脸核身
     */
    private fun initUserDetect(bizToken: String?): Observable<String> {
        return Observable.create {
            FaceIdManager.getInstance(AppManager.instance.currentActivity.application).apply {
                setFaceIdInitListener(object : FaceIdInitListener {
                    override fun onSuccess() {
                        "人脸核身初始化成功".log()
                        it.onNext("")
                        it.onComplete()
                    }

                    override fun onFailed(code: Int, msg: String?) {
                        "人脸核身初始化失败 : code=$code,msg=$msg".log()
                        it.onError(BaseException(code, msg))
                    }
                })
                init(bizToken)
            }
        }
    }

    /**
     * 验证身份证
     */
    private fun detectIdCard(bizToken: String?): Observable<IDCardResult?> {
        return Observable.create {
            IDCardManager.getInstance().setIdCardDetectListener { result ->
                "身份证识别结束 : resultCode=${result.resultCode},resultMessage=${result.resultMessage}".log()
                if (result.resultCode == 1001 || result.resultCode == 1002) {
                    it.onNext(result)
                    it.onComplete()
                } else {
                    it.onError(BaseException(ID_CARD_CANT_BE_DETECTED, "id card can't be detected"))
                }
            }
            IDCardManager.getInstance()
                .startDetect(AppManager.instance.currentActivity.applicationContext, bizToken, "")
        }
    }

    /**
     * 验证人脸
     */
    private fun detectUser(): Observable<UserDetectResultBean?> {
        return Observable.create {
            val applicationContext = AppManager.instance.currentActivity.applicationContext
            FaceIdManager.getInstance(applicationContext).apply {
                setFaceIdDetectListener(object : FaceIdDetectListener {
                    override fun onSuccess(code: Int, msg: String?) {
                        it.onNext(UserDetectResultBean(code, msg))
                        it.onComplete()
                    }

                    override fun onFailed(code: Int, msg: String?) {
                        it.onError(BaseException(code, msg))
                    }
                })
                startDetect()
            }
        }
    }
}
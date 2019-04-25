package com.dong.dapp.network

import com.dong.dapp.bean.kyc.*
import com.dong.dapp.bean.login.LoginBean
import com.dong.dapp.bean.login.VerifyCodeBean
import com.dong.dapp.bean.wallet.*
import com.dong.dapp.extensions.decrypt
import com.dong.dapp.network.api.*
import io.reactivex.Observable
import me.serenadehl.base.extensions.async
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 19:46:44
 */
object DAppRequest {

    //=============================================通用接口=============================================

    /**
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getDApps(page: Int, pageSize: Int): Observable<ProjectListBean?> {
        return RetrofitHelper.create(CommonApi::class.java)
            .getDApps(page, pageSize)
            .decrypt<ProjectListBean?>()
            .async()
    }

    /**
     * 上传文件
     */
    fun uploadFile(content: ByteArray): Observable<UploadFileBean?> {
        val requestBody = RequestBody.create(MediaType.parse("image/jpg"), content)
        val body = MultipartBody.Part.createFormData("file", "IdCard.jpg", requestBody)
        return RetrofitHelper.create(CommonApi::class.java)
            .uploadFile(body)
            .decrypt<UploadFileBean?>()
            .async()
    }


    //=============================================登录接口=============================================

    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String): Observable<VerifyCodeBean?> {
        return RetrofitHelper.create(LoginApi::class.java)
            .getVerifyCode(phone, 0, areaCode)
            .decrypt<VerifyCodeBean?>()
            .async()
    }

    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param registered getVerifyCode接口返回数据
     */
    fun login(verifyCode: String, fp: String, registered: String): Observable<LoginBean?> {
        return RetrofitHelper.create(LoginApi::class.java)
            .login(verifyCode, fp, registered, 2)
            .decrypt<LoginBean?>()
            .async()
    }


    //=============================================Tron相关接口=============================================

    /**
     * 获取Tron用户信息
     */
    fun getTronUserInfo(): Observable<UserInfoBean?> {
        return RetrofitHelper.create(TronApi::class.java)
            .getTronUserInfo("product")
            .decrypt<UserInfoBean?>()
            .async()
    }

    /**
     * 获取Tron签名
     * @param publicKey
     * @param message
     * @param type
     */
    fun getTronSign(publicKey: String, message: String, type: String): Observable<TronSignBean?> {
        return RetrofitHelper.create(TronApi::class.java)
            .getTronSign(publicKey, message, type, "product")
            .decrypt<TronSignBean?>()
            .async()
    }


    //=============================================KYC相关接口=============================================

    /**
     * 获取智能KYC的BizToken
     */
    //TODO 无解密
    fun getKYCBizToken(
        sign: String,
        idCardName: String,
        idCardNumber: String
    ): Observable<KYCBizTokenBean?> {
        return RetrofitHelper.create(KYCApi::class.java)
            .getKYCBizToken(sign, idCardName, idCardNumber, "hmac_sha1", "meglive", 1)
            .async()
    }

    /**
     * 获取智能KYC的签名
     */
    fun getKYCSign(): Observable<KYCSignBean?> {
        return RetrofitHelper.create(KYCApi::class.java)
            .getKYCSign()
            .decrypt<KYCSignBean?>()
            .async()
    }

    /**
     * 判断身份证号是否可用
     * @param idNumber 身份证号
     */
    fun isIdCardNumberAvailable(idNumber: String): Observable<IdCardNumberAvailableBean?> {
        return RetrofitHelper.create(KYCApi::class.java)
            .isIdCardNumberAvailable(idNumber)
            .decrypt<IdCardNumberAvailableBean?>()
            .async()
    }

    /**
     * KYC验证完成通知后端
     */
    fun finishKYC(info: KYCInfoBean): Observable<FinishKYCBean?> {
        return RetrofitHelper.create(KYCApi::class.java)
            .finishKYC(info)
            .decrypt<FinishKYCBean?>()
            .async()
    }
}
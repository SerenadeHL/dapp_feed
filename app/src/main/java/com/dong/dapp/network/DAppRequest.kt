package com.dong.dapp.network

import com.dong.dapp.bean.kyc.*
import com.dong.dapp.bean.wallet.*
import com.dong.dapp.network.api.*
import io.reactivex.Observable
import me.serenadehl.base.extensions.async
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 19:46:44
 */
object DAppRequest {
    fun test(test: String): Observable<BaseResponse<String?>> {
        return RetrofitHelper.create(TestApi::class.java)
            .test(test)
            .async()
    }

    //=============================================通用接口=============================================

    /**
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getDApps(page: Int, pageSize: Int): Observable<BaseResponse<ProjectListBean?>> {
        return RetrofitHelper.create(CommonApi::class.java)
            .getDApps(page, pageSize)
            .async()
    }

    /**
     * 上传文件
     */
    fun uploadFile(content: ByteArray): Observable<BaseResponse<UploadFileBean?>> {
        val requestBody = RequestBody.create(MediaType.parse("image/jpg"), content)
        val body = MultipartBody.Part.createFormData("file", "IdCard.jpg", requestBody)
        return RetrofitHelper.create(CommonApi::class.java)
            .uploadFile(body)
            .async()
    }


    //=============================================登录接口=============================================

    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String): Observable<BaseResponse<VerifyCodeBean?>> {
        return RetrofitHelper.create(LoginApi::class.java)
            .getVerifyCode(phone, 0, areaCode)
            .async()
    }

    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param registered getVerifyCode接口返回数据
     */
    fun login(verifyCode: String, fp: String, registered: String): Observable<BaseResponse<LoginBean?>> {
        return RetrofitHelper.create(LoginApi::class.java)
            .login(verifyCode, fp, registered, 2)
            .async()
    }


    //=============================================Tron相关接口=============================================

    /**
     * 获取Tron用户信息
     */
    fun getTronUserInfo(): Observable<BaseResponse<UserInfoBean?>> {
        return RetrofitHelper.create(TronApi::class.java)
            .getTronUserInfo("product")
            .async()
    }

    /**
     * 获取Tron签名
     * @param publicKey
     * @param message
     * @param type
     */
    fun getTronSign(publicKey: String, message: String, type: String): Observable<BaseResponse<TronSignBean?>> {
        return RetrofitHelper.create(TronApi::class.java)
            .getTronSign(publicKey, message, type, "product")
            .async()
    }


    //=============================================KYC相关接口=============================================

    /**
     * 获取智能KYC的BizToken
     */
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
    fun getKYCSign(): Observable<BaseResponse<KYCSignBean?>> {
        return RetrofitHelper.create(KYCApi::class.java)
            .getKYCSign()
            .async()
    }

    /**
     * 判断身份证号是否可用
     * @param idNumber 身份证号
     */
    fun isIdCardNumberAvailable(idNumber: String): Observable<BaseResponse<IdCardNumberAvailableBean?>> {
        return RetrofitHelper.create(KYCApi::class.java)
            .isIdCardNumberAvailable(idNumber)
            .async()
    }

    /**
     * KYC验证完成通知后端
     */
    fun finishKYC(info: KYCInfoBean): Observable<BaseResponse<FinishKYCBean?>> {
        return RetrofitHelper.create(KYCApi::class.java)
            .finishKYC(info)
            .async()
    }
}
package com.dong.dapp.network.api

import com.dong.dapp.bean.kyc.*
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:10:09
 */
interface KYCApi {
    /**
     * 获取智能KYC的BizToken
     */
    @POST("https://openapi.faceid.com/face/v1.2/sdk/get_biz_token")
    @FormUrlEncoded
    fun getKYCBizToken(
        @Field("sign") sign: String,
        @Field("idcard_name") idCardName: String,
        @Field("idcard_number") idCardNumber: String,
        @Field("sign_version") signVersion: String,
        @Field("liveness_type") livenessType: String,
        @Field("comparison_type") comparisonType: Int
    ): Observable<ResultKYCBizTokenBean?>

    /**
     * 获取智能KYC的签名
     */
    @POST("/api/v1/faceid/get_app_sign")
    fun getKYCSign(): Observable<BaseResponse>

    /**
     * 判断身份证号是否可用
     * @param idNumber 身份证号
     */
    @POST("/api/v1/faceid/is_right_id_no")
    fun isIdCardNumberAvailable(@Body requestBean: RequestIdCardNumberAvailableBean): Observable<BaseResponse>

    /**
     * KYC验证完成通知后端
     */
    @POST("/api/v1/faceid/app_result")
    fun finishKYC(@Body requestBean: RequestKYCInfoBean): Observable<BaseResponse>
}
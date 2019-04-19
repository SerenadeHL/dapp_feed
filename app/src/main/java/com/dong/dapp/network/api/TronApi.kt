package com.dong.dapp.network.api

import com.dong.dapp.bean.wallet.TronSignBean
import com.dong.dapp.bean.wallet.UserInfoBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:12:10
 */
interface TronApi {
    /**
     * 获取Tron用户信息
     * @param env 线上环境为product
     */
    @POST("public/wallet/tron_userinfo")
    @FormUrlEncoded
    fun getTronUserInfo(@Field("env") env: String): Observable<BaseResponse<UserInfoBean?>>

    /**
     * 获取Tron签名
     * @param publicKey
     * @param message
     * @param type
     * @param env 线上环境为product
     */
    @POST("public/wallet/tron_sign")
    @FormUrlEncoded
    fun getTronSign(
        @Field("public_key") publicKey: String,
        @Field("message") message: String,
        @Field("type") type: String,
        @Field("env") env: String
    ): Observable<BaseResponse<TronSignBean?>>

}
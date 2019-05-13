package com.axonomy.dapp_feed.network.api

import com.axonomy.dapp_feed.bean.dapp.RequestPublicKeyBean
import com.axonomy.dapp_feed.bean.dapp.RequestSignBean
import com.axonomy.dapp_feed.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:12:10
 */
interface DAppApi {
    /**
     * 获取公钥
     */
    @POST("api/v1/wallet/pubkey")
    fun getTronPublicKey(@Body requestBean: RequestPublicKeyBean): Observable<BaseResponse>

    /**
     * 获取签名
     */
    @POST("api/v1/wallet/sign")
    fun getTronSign(@Body requestBean: RequestSignBean): Observable<BaseResponse>

}
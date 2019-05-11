package com.axonomy.dapp_feed.network.api

import com.axonomy.dapp_feed.bean.recharge.RequestRechargeOrderBean
import com.axonomy.dapp_feed.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 充值相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:45:14
 */
interface RechargeApi {

    /**
     * 获取充值商品列表
     */
    @GET("api/df/asset/coin/trx/deposit/products")
    fun getRechargeOptions(): Observable<BaseResponse>

    /**
     * 获取充值订单信息
     * @param product_id 商品id
     */
    @POST("api/df/asset/coin/trx/deposit/order")
    fun getRechargeOrder(@Body requestBean: RequestRechargeOrderBean): Observable<BaseResponse>

}
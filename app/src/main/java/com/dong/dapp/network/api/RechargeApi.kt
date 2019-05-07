package com.dong.dapp.network.api

import com.dong.dapp.bean.recharge.RequestRechargeOrderBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET

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
    @GET("api/df/asset/coin/trx/deposit/order")
    fun getRechargeOrder(@Body requestBean: RequestRechargeOrderBean): Observable<BaseResponse>

}
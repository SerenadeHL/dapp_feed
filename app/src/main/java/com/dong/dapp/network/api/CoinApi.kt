package com.dong.dapp.network.api

import com.dong.dapp.bean.multipage.RequestMultiPageBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 金币相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:50:53
 */
interface CoinApi {
    /**
     * 获取金币资产
     */
    @GET("api/df/assets/coin/trx/balance")
    fun getCoinBalance(): Observable<BaseResponse>

    /**
     * 获取金币流水
     */
    @GET("api/df/asset/coin/trx/change/records")
    fun getCoinRecords(@Query("page") page: Int, @Query("page_size") pageSize: Int): Observable<BaseResponse>
}
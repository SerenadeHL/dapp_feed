package com.dong.dapp.network.api

import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 现金相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:31:35
 */
interface CashApi{
    /**
     * 获取现金资产
     */
    @GET(" assets/cash/balance")
    fun getCashBalance(): Observable<BaseResponse>

    /**
     * 获取现金流水
     */
    @GET("asset/cash/change/records")
    fun getCashRecords(@Query("page") page: Int, @Query("page_size") pageSize: Int): Observable<BaseResponse>

}
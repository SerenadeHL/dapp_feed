package com.dong.dapp.network.api

import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 现金相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:31:35
 */
interface CashApi {
    /**
     * 获取现金每日收益
     */
    @GET("api/df/user/me/today/cash/revenue")
    fun getCashDailyIncome(): Observable<BaseResponse>

    /**
     * 获取现金资产
     */
    @GET("api/df/assets/cash/balance")
    fun getCashBalance(): Observable<BaseResponse>

    /**
     * 获取现金流水
     */
    @GET("api/df/asset/cash/change/records")
    fun getCashRecords(@Query("page") page: Int, @Query("page_size") pageSize: Int): Observable<BaseResponse>


    /**
     * 获取现金流水详情
     */
    @GET("api/df/asset/cash/change/record/{record_id}")
    fun getCashRecordDetail(@Path("record_id") recordId: String): Observable<BaseResponse>
}
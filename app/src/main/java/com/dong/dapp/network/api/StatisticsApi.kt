package com.dong.dapp.network.api

import com.dong.dapp.bean.statistics.RequestEnterDAppBean
import com.dong.dapp.bean.statistics.RequestExitDAppBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 17:34:50
 */
interface StatisticsApi {
    /**
     * 进入DApp
     * @param pid DApp的id
     */
    @POST("/api/v1/dapp/enter")
    fun enterDApp(@Body requestBean: RequestEnterDAppBean): Observable<BaseResponse>

    /**
     * 退出DApp
     * @param id 用户行为id
     * @param action 用户行为记录，格式为：[时间戳:-1]
     */
    @POST("/api/v1/dapp/quit")
    fun exitDApp(@Body requestBean: RequestExitDAppBean): Observable<BaseResponse>
}
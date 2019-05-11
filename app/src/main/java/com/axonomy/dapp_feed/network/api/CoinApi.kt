package com.axonomy.dapp_feed.network.api

import com.axonomy.dapp_feed.bean.coin.RequestCoinBalanceBean
import com.axonomy.dapp_feed.bean.coin.RequestCoinRecordsBean
import com.axonomy.dapp_feed.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 金币相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:50:53
 */
interface CoinApi {
    /**
     * 获取金币资产
     * @param protocol 公链类型 tron 0 EOS 1 ETH 2
     */
    @POST("api/v1/wallet/details")
    fun getCoinBalance(@Body requestBean: RequestCoinBalanceBean): Observable<BaseResponse>

    /**
     * 获取金币流水
     * @param protocol 公链类型 tron 0 EOS 1 ETH 2
     * @param page 页数
     * @param page_size 条数
     */
    @POST("api/v1/wallet/txns")
    fun getCoinRecords(@Body requestBean: RequestCoinRecordsBean): Observable<BaseResponse>
}
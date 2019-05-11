package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcoincount

import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinRecordsBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
interface ITotalCoinCountView : IBaseView {
    fun getCoinBalanceSuccess(data: ResultCoinBalanceBean?)

    fun getCoinBalanceFailed()

    fun getCoinRecordsSuccess(data: ResultCoinRecordsBean?, refresh: Boolean)

    fun getCoinRecordsFailed()
}

interface ITotalCoinCountPresenter : IBasePresenter {
    /**
     * 获取金币资产
     */
    fun getCoinBalance()

    /**
     * 获取金币流水
     * @param page 页数
     * @param pageSize 条数
     */
    fun getCoinRecords(page: Int, pageSize: Int, refresh: Boolean)
}

interface ITotalCoinCountModel : IBaseModel {
    /**
     * 获取金币资产
     */
    fun getCoinBalance(): Observable<ResultCoinBalanceBean?>

    /**
     * 获取金币流水
     * @param page 页数
     * @param pageSize 条数
     */
    fun getCoinRecords(page: Int, pageSize: Int): Observable<ResultCoinRecordsBean?>
}

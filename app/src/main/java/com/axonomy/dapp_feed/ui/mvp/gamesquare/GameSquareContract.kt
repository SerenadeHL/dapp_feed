package com.axonomy.dapp_feed.ui.mvp.gamesquare

import com.axonomy.dapp_feed.bean.cash.ResultCashDailyIncomeBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultAnnouncementBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
interface IGameSquareView : IBaseView {
    fun getAnnouncementSuccess(data: ResultAnnouncementBean?)

    fun getAnnouncementFailed()

    fun getDAppListSuccess(data: ResultDAppBean?)

    fun getDAppListFailed()

    fun getDailyCoinIncomeSuccess(data: ResultCoinBalanceBean?)

    fun getDailyCoinIncomeFailed()

    fun getDailyCashIncomeSuccess(data: ResultCashDailyIncomeBean?)

    fun getDailyCashIncomeFailed()
}

interface IGameSquarePresenter : IBasePresenter {
    /**
     * 获取公告
     */
    fun getAnnouncement()

    /**
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getDAppList(page: Int, pageSize: Int)

    /**
     * 获取每日金币收益
     */
    fun getDailyCoinIncome()

    /**
     * 获取每日现金收益
     */
    fun getDailyCashIncome()
}

interface IGameSquareModel : IBaseModel {
    /**
     * 获取公告
     */
    fun getAnnouncement(): Observable<ResultAnnouncementBean?>

    /**
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getDAppList(page: Int, pageSize: Int): Observable<ResultDAppBean?>

    /**
     * 获取每日金币收益
     */
    fun getDailyCoinIncome(): Observable<ResultCoinBalanceBean?>

    /**
     * 获取每日现金收益
     */
    fun getDailyCashIncome(): Observable<ResultCashDailyIncomeBean?>
}

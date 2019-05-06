package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.cash.ResultCashBalanceBean
import com.dong.dapp.bean.coin.ResultCoinBalanceBean
import com.dong.dapp.bean.gamesquare.ResultAnnouncementBean
import com.dong.dapp.bean.gamesquare.ResultDAppBean
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

    fun getTodayCoinIncomeSuccess(data: ResultCoinBalanceBean?)

    fun getTodayCoinIncomeFailed()

    fun getTodayCashIncomeSuccess(data: ResultCashBalanceBean?)

    fun getTodayCashIncomeFailed()
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
     * 获取今日金币收益
     */
    fun getTodayCoinIncome()

    /**
     * 获取今日现金收益
     */
    fun getTodayCashIncome()
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
     * 获取今日金币收益
     */
    fun getTodayCoinIncome(): Observable<ResultCoinBalanceBean?>

    /**
     * 获取今日现金收益
     */
    fun getTodayCashIncome(): Observable<ResultCashBalanceBean?>
}

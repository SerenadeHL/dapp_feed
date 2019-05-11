package com.axonomy.dapp_feed.ui.mvp.gamesquare

import com.axonomy.dapp_feed.bean.cash.ResultCashDailyIncomeBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultAnnouncementBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppBean
import com.axonomy.dapp_feed.constant.Constant
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquareModel : MVPBaseModel(), IGameSquareModel {

    override fun getAnnouncement(): Observable<ResultAnnouncementBean?> {
        return RequestManager.getAnnouncement()
    }

    override fun getDAppList(page: Int, pageSize: Int): Observable<ResultDAppBean?> {
        return RequestManager.getDAppList(page, pageSize)
    }

    override fun getDailyCoinIncome(): Observable<ResultCoinBalanceBean?> {
        return RequestManager.getCoinBalance(Constant.TRON)
    }

    override fun getDailyCashIncome(): Observable<ResultCashDailyIncomeBean?> {
        return RequestManager.getCashDailyIncome()
    }
}

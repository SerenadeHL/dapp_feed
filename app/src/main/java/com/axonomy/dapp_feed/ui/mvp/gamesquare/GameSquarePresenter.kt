package com.axonomy.dapp_feed.ui.mvp.gamesquare

import com.axonomy.dapp_feed.bean.cash.ResultCashDailyIncomeBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultAnnouncementBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquarePresenter : MVPBasePresenter<IGameSquareView, IGameSquareModel>(), IGameSquarePresenter {

    override fun createModel() = GameSquareModel()

    override fun getAnnouncement() {
        mModel.getAnnouncement()
            .subscribe(object : BaseObserver<ResultAnnouncementBean?>() {
                override fun next(data: ResultAnnouncementBean?) {
                    mView.get()?.getAnnouncementSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getAnnouncementFailed()
                }
            })
    }

    override fun getDAppList(page: Int, pageSize: Int) {
        mModel.getDAppList(page, pageSize)
            .subscribe(object : BaseObserver<ResultDAppBean?>() {
                override fun next(data: ResultDAppBean?) {
                    mView.get()?.getDAppListSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getDAppListFailed()
                }
            })
    }

    override fun getDailyCoinIncome() {
        mModel.getDailyCoinIncome()
            .subscribe(object : BaseObserver<ResultCoinBalanceBean?>() {
                override fun next(data: ResultCoinBalanceBean?) {
                    mView.get()?.getDailyCoinIncomeSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getDailyCoinIncomeFailed()
                }
            })
    }

    override fun getDailyCashIncome() {
        mModel.getDailyCashIncome()
            .subscribe(object : BaseObserver<ResultCashDailyIncomeBean?>() {
                override fun next(data: ResultCashDailyIncomeBean?) {
                    mView.get()?.getDailyCashIncomeSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getDailyCashIncomeFailed()
                }
            })
    }
}

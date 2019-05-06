package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.cash.ResultCashBalanceBean
import com.dong.dapp.bean.coin.ResultCoinBalanceBean
import com.dong.dapp.bean.gamesquare.ResultAnnouncementBean
import com.dong.dapp.bean.gamesquare.ResultDAppBean
import com.dong.dapp.network.BaseObserver
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

    override fun getTodayCoinIncome() {
        mModel.getTodayCoinIncome()
            .subscribe(object : BaseObserver<ResultCoinBalanceBean?>() {
                override fun next(data: ResultCoinBalanceBean?) {
                    mView.get()?.getTodayCoinIncomeSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getTodayCoinIncomeFailed()
                }
            })
    }

    override fun getTodayCashIncome() {
        mModel.getTodayCashIncome()
            .subscribe(object : BaseObserver<ResultCashBalanceBean?>() {
                override fun next(data: ResultCashBalanceBean?) {
                    mView.get()?.getTodayCashIncomeSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getTodayCashIncomeFailed()
                }
            })
    }
}

package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcoincount

import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinRecordsBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountPresenter : MVPBasePresenter<ITotalCoinCountView, ITotalCoinCountModel>(),
    ITotalCoinCountPresenter {

    override fun createModel() = TotalCoinCountModel()

    override fun getCoinBalance() {
        mModel.getCoinBalance()
            .subscribe(object : BaseObserver<ResultCoinBalanceBean?>() {
                override fun next(data: ResultCoinBalanceBean?) {
                    mView.get()?.getCoinBalanceSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCoinBalanceFailed()
                }
            })
    }

    override fun getCoinRecords(page: Int, pageSize: Int, refresh: Boolean) {
        mModel.getCoinRecords(page, pageSize)
            .subscribe(object : BaseObserver<ResultCoinRecordsBean?>() {
                override fun next(data: ResultCoinRecordsBean?) {
                    mView.get()?.getCoinRecordsSuccess(data, refresh)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCoinRecordsFailed()
                }
            })
    }
}

package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcashcount

import com.axonomy.dapp_feed.bean.cash.ResultCashBalanceBean
import com.axonomy.dapp_feed.bean.cash.ResultCashRecordsBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
class TotalCashCountPresenter : MVPBasePresenter<ITotalCashCountView, ITotalCashCountModel>(),
    ITotalCashCountPresenter {

    override fun createModel() = TotalCashCountModel()

    override fun getCashBalance() {
        mModel.getCashBalance()
            .subscribe(object : BaseObserver<ResultCashBalanceBean?>() {
                override fun next(data: ResultCashBalanceBean?) {
                    mView.get()?.getCashBalanceSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCashBalanceFailed()
                }
            })
    }

    override fun getCashRecords(page: Int, pageSize: Int, refresh: Boolean) {
        mModel.getCashRecords(page, pageSize)
            .subscribe(object : BaseObserver<ResultCashRecordsBean?>() {
                override fun next(data: ResultCashRecordsBean?) {
                    mView.get()?.getCashRecordsSuccess(data, refresh)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCashRecordsFailed()
                }
            })
    }
}

package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercashdetail

import com.axonomy.dapp_feed.bean.cash.ResultCashRecordDetailBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.addDisposable

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 12:24:05
 */
class TransferCashDetailPresenter : MVPBasePresenter<ITransferCashDetailView, ITransferCashDetailModel>(),
    ITransferCashDetailPresenter {

    override fun createModel() = TransferCashDetailModel()

    override fun getCashRecordDetail(recordId: String) {
        mModel.getCashRecordDetail(recordId)
            .addDisposable(mCompositeDisposable)
            .subscribe(object : BaseObserver<ResultCashRecordDetailBean?>() {
                override fun next(data: ResultCashRecordDetailBean?) {
                    mView.get()?.getCashRecordDetailSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCashRecordDetailFailed()
                }
            })
    }
}
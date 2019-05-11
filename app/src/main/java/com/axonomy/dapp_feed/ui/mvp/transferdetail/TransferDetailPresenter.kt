package com.axonomy.dapp_feed.ui.mvp.transferdetail

import com.axonomy.dapp_feed.bean.cash.ResultCashRecordDetailBean
import com.axonomy.dapp_feed.network.BaseObserver
import com.axonomy.dapp_feed.ui.mvp.transfer.ITransferView
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 12:24:05
 */
class TransferDetailPresenter : MVPBasePresenter<ITransferDetailView, ITransferDetailModel>(),
    ITransferDetailPresenter {

    override fun createModel() = TransferDetailModel()

    override fun getCashRecordDetail(recordId: String) {
        mModel.getCashRecordDetail(recordId)
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
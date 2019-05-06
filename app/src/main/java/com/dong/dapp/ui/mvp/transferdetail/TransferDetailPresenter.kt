package com.dong.dapp.ui.mvp.transferdetail

import com.dong.dapp.bean.cash.ResultCashRecordDetailBean
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.ui.mvp.transfer.ITransferView
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
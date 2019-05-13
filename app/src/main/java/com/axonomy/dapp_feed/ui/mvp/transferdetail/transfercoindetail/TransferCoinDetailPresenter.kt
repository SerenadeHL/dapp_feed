package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercoindetail

import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 10:26:18
 */
class TransferCoinDetailPresenter : MVPBasePresenter<ITransferCoinDetailView, ITransferCoinDetailModel>(),
    ITransferCoinDetailPresenter {

    override fun createModel() = TransferCoinDetailModel()

}
package com.dong.dapp.ui.mvp.transfer

import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 15:29:32
 */
class TransferPresenter : MVPBasePresenter<ITransferView, ITransferModel>(), ITransferPresenter {
    override fun createModel() = TransferModel()

}
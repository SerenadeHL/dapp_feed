package com.dong.dapp.ui.mvp.totalcoincount

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

}

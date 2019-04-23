package com.dong.dapp.ui.mvp.totalcashcount

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

}

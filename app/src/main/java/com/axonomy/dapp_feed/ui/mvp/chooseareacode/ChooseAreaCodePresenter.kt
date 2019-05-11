package com.axonomy.dapp_feed.ui.mvp.chooseareacode

import android.annotation.SuppressLint
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
class ChooseAreaCodePresenter : MVPBasePresenter<IChooseAreaCodeView, IChooseAreaCodeModel>(),
    IChooseAreaCodePresenter {

    override fun createModel() = ChooseAreaCodeModel()

    @SuppressLint("CheckResult")
    override fun getAreaCode() {
        mModel.getAreaCode()
            .subscribe {
                mView.get()?.setAreaCode(it!!)
            }
    }
}

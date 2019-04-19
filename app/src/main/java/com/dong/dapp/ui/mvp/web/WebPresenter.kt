package com.dong.dapp.ui.mvp.web

import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
class WebPresenter : MVPBasePresenter<IWebView, IWebModel>(), IWebPresenter {

    override fun createModel() = WebModel()

}

package com.axonomy.dapp_feed.ui.mvp.web

import com.axonomy.dapp_feed.bean.dapp.ResultPublicKeyBean
import com.axonomy.dapp_feed.bean.statistics.ResultEnterDAppBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.addDisposable

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
class DAppWebPresenter : MVPBasePresenter<IDAppWebView, IDAppWebModel>(), IDAppWebPresenter {

    override fun createModel() = DAppWebModel()

    override fun enterDApp(pid: String) {
        mModel.enterDApp(pid)
            .subscribe(object : BaseObserver<ResultEnterDAppBean?>() {
                override fun next(data: ResultEnterDAppBean?) {
                    mView.get()?.enterDAppSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.enterDAppFailed()
                }
            })
    }

    override fun exitDApp(id: String, action: List<Map<String, String>>) {
        mModel.exitDApp(id, action)
            .subscribe()
    }

    override fun getPublicKey() {
        mModel.getPublicKey()
            .subscribe(object : BaseObserver<ResultPublicKeyBean?>() {
                override fun next(data: ResultPublicKeyBean?) {
                    mView.get()?.getPublicKeySuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getPublicKeyFailed()
                }
            })
    }
}

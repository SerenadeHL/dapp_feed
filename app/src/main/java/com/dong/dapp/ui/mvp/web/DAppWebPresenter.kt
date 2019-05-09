package com.dong.dapp.ui.mvp.web

import com.dong.dapp.bean.statistics.ResultEnterDAppBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

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
}

package com.axonomy.dapp_feed.ui.mvp.settings

import com.axonomy.dapp_feed.bean.update.ResultUpdateInfoBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 15:40:49
 */

class SettingsPresenter : MVPBasePresenter<ISettingsView, ISettingsModel>(), ISettingsPresenter {

    override fun createModel() = SettingsModel()

    override fun getUpdateInfo() {
        mModel.getUpdateInfo()
            .subscribe(object : BaseObserver<ResultUpdateInfoBean?>(){
                override fun next(data: ResultUpdateInfoBean?) {
                    mView.get()?.getUpdateInfoSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getUpdateInfoFailed()
                }

            })
    }
}
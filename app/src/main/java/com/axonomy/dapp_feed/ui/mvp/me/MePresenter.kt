package com.axonomy.dapp_feed.ui.mvp.me

import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
class MePresenter : MVPBasePresenter<IMeView, IMeModel>(), IMePresenter {

    override fun createModel() = MeModel()

    override fun getUserInfo() {
        mModel.getUserInfo()
            .subscribe(object : BaseObserver<ResultUserInfoBean?>() {
                override fun next(data: ResultUserInfoBean?) {
                    mView.get()?.getUserInfoSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getUserInfoFailed()
                }
            })
    }
}

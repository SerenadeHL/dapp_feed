package com.axonomy.dapp_feed.ui.mvp.splash

import com.axonomy.dapp_feed.bean.common.ResultCommonConfigurationBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.addDisposable

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:40:09
 */
class SplashPresenter : MVPBasePresenter<ISplashView, ISplashModel>(), ISplashPresenter {
    override fun createModel() = SplashModel()

    override fun getCommonConfiguration() {
        mModel.getCommonConfiguration()
            .addDisposable(mCompositeDisposable)
            .subscribe(object : BaseObserver<ResultCommonConfigurationBean?>() {
                override fun next(data: ResultCommonConfigurationBean?) {
                    mView.get()?.getCommonConfigurationSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCommonConfigurationFailed()
                }
            })
    }

    override fun getUserInfo() {
        mModel.getUserInfo()
            .addDisposable(mCompositeDisposable)
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
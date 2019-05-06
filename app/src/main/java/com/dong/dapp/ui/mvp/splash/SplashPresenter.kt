package com.dong.dapp.ui.mvp.splash

import com.dong.dapp.bean.common.ResultCommonConfigurationBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:40:09
 */
class SplashPresenter : MVPBasePresenter<ISplashView, ISplashModel>(), ISplashPresenter {

    override fun createModel() = SplashModel()

    override fun getCommonConfiguration() {
        mModel.getCommonConfiguration()
            .subscribe(object : BaseObserver<ResultCommonConfigurationBean?>() {
                override fun next(data: ResultCommonConfigurationBean?) {
                    mView.get()?.getCommonConfigurationSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getCommonConfigurationFailed()
                }
            })
    }
}
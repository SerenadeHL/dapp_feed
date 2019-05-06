package com.dong.dapp.ui.mvp.me

import com.dong.dapp.bean.me.ResultUserInfoBean
import com.dong.dapp.network.BaseObserver
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

package com.dong.dapp.ui.mvp.main

import com.dong.dapp.bean.update.ResultUpdateInfoBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 10:06:01
 */
class MainPresenter : MVPBasePresenter<IMainView, IMainModel>(), IMainPresenter {

    override fun createModel() = MainModel()

    override fun getUpdateInfo() {
        mModel.getUpdateInfo()
            .subscribe(object :BaseObserver<ResultUpdateInfoBean?>(){

                override fun next(data: ResultUpdateInfoBean?) {
                    mView.get()?.getUpdateInfoSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getUpdateInfoFailed()
                }

            })
    }
}
package com.dong.dapp.ui.mvp.kyc

import com.dong.dapp.bean.kyc.ResultIdCardNumberAvailableBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:49:08
 */
class KYCPresenter : MVPBasePresenter<IKYCView, IKYCModel>(), IKYCPresenter {

    override fun createModel() = KYCModel()

    override fun checkIdCarNumber(idNumber: String) {
        mModel.checkIdCarNumber(idNumber)
            .subscribe(object : BaseObserver<ResultIdCardNumberAvailableBean?>() {
                override fun next(data: ResultIdCardNumberAvailableBean?) {
                    mView.get()?.checkIdCarNumberSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.checkIdCarNumberFailed()
                }
            })
    }
}
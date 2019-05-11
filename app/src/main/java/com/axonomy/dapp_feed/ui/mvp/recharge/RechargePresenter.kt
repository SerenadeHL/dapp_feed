package com.axonomy.dapp_feed.ui.mvp.recharge

import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOptionsBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOrderBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
class RechargePresenter : MVPBasePresenter<IRechargeView, IRechargeModel>(), IRechargePresenter {

    override fun createModel() = RechargeModel()

    override fun getRechargeOptions() {
        mModel.getRechargeOptions()
            .subscribe(object : BaseObserver<ResultRechargeOptionsBean?>() {
                override fun next(data: ResultRechargeOptionsBean?) {
                    mView.get()?.getRechargeOptionsSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getRechargeOptionsFailed()
                }
            })
    }

    override fun getRechargeOrder(id: String) {
        mModel.getRechargeOrder(id)
            .subscribe(object : BaseObserver<ResultRechargeOrderBean?>() {
                override fun next(data: ResultRechargeOrderBean?) {
                    mView.get()?.getRechargeOrderSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getRechargeOrderFailed()
                }
            })
    }
}

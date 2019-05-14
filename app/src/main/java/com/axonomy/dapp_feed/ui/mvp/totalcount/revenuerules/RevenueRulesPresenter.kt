package com.axonomy.dapp_feed.ui.mvp.totalcount.revenuerules

import com.axonomy.dapp_feed.bean.coin.ResultRevenueRulesBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-14 12:00:24
 */
class RevenueRulesPresenter : MVPBasePresenter<IRevenueRulesView, IRevenueRulesModel>(), IRevenueRulesPresenter {

    override fun createModel() = RevenueRulesModel()

    override fun getRevenueRules() {
        mModel.getRevenueRules()
            .subscribe(object : BaseObserver<ResultRevenueRulesBean?>() {
                override fun next(data: ResultRevenueRulesBean?) {
                    mView.get()?.getRevenueRulesSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getRevenueRulesFailed()
                }
            })
    }
}

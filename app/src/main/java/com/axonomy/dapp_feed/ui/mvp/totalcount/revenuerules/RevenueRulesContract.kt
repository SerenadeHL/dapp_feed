package com.axonomy.dapp_feed.ui.mvp.totalcount.revenuerules

import com.axonomy.dapp_feed.bean.coin.ResultRevenueRulesBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-14 12:00:24
 */
interface IRevenueRulesView : IBaseView {
    fun getRevenueRulesSuccess(data: ResultRevenueRulesBean?)

    fun getRevenueRulesFailed()
}

interface IRevenueRulesPresenter : IBasePresenter {
    /**
     * 获取收益规则说明
     */
    fun getRevenueRules()
}

interface IRevenueRulesModel : IBaseModel {
    /**
     * 获取收益规则说明
     */
    fun getRevenueRules(): Observable<ResultRevenueRulesBean?>
}

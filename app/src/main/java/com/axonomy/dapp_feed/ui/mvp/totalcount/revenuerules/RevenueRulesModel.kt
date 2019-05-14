package com.axonomy.dapp_feed.ui.mvp.totalcount.revenuerules

import com.axonomy.dapp_feed.bean.coin.ResultRevenueRulesBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-14 12:00:24
 */
class RevenueRulesModel : MVPBaseModel(), IRevenueRulesModel {
    override fun getRevenueRules(): Observable<ResultRevenueRulesBean?> {
        return RequestManager.getRevenueRules()
    }
}

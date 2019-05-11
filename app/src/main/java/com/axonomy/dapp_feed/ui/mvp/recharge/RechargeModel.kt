package com.axonomy.dapp_feed.ui.mvp.recharge

import com.axonomy.dapp_feed.bean.recharge.RequestRechargeOrderBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOptionsBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOrderBean
import com.axonomy.dapp_feed.extensions.decrypt
import com.axonomy.dapp_feed.network.RequestManager
import com.axonomy.dapp_feed.network.RetrofitHelper
import com.axonomy.dapp_feed.network.api.RechargeApi
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel
import me.serenadehl.base.extensions.async

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
class RechargeModel : MVPBaseModel(), IRechargeModel {

    override fun getRechargeOptions(): Observable<ResultRechargeOptionsBean?> {
        return RequestManager.getRechargeOptions()
    }

    /**
     * 获取充值订单信息
     * @param product_id 商品id
     */
    override fun getRechargeOrder(id: String): Observable<ResultRechargeOrderBean?> {
        return RequestManager.getRechargeOrder(id)
    }
}

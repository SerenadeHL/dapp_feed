package com.axonomy.dapp_feed.ui.mvp.recharge

import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOptionsBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOrderBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
interface IRechargeView : IBaseView {
    /**
     * 获取充值商品列表成功
     */
    fun getRechargeOptionsSuccess(data: ResultRechargeOptionsBean?)

    /**
     * 获取充值商品列表失败
     */
    fun getRechargeOptionsFailed()

    /**
     * 获取充值订单信息成功
     */
    fun getRechargeOrderSuccess(data: ResultRechargeOrderBean?)

    /**
     * 获取充值订单信息失败
     */
    fun getRechargeOrderFailed()
}

interface IRechargePresenter : IBasePresenter {
    /**
     * 获取充值商品列表
     */
    fun getRechargeOptions()

    /**
     * 获取充值订单信息
     * @param product_id 商品id
     */
    fun getRechargeOrder(id: String)
}

interface IRechargeModel : IBaseModel {
    /**
     * 获取充值商品列表
     */
    fun getRechargeOptions(): Observable<ResultRechargeOptionsBean?>

    /**
     * 获取充值订单信息
     * @param product_id 商品id
     */
    fun getRechargeOrder(id: String): Observable<ResultRechargeOrderBean?>
}

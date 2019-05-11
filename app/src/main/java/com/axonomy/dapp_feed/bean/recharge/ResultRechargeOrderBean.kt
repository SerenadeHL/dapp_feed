package com.axonomy.dapp_feed.bean.recharge

import com.google.gson.annotations.SerializedName

/**
 * 充值下单结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:58:46
 */
data class ResultRechargeOrderBean(
    @SerializedName("alipay_payment_order_info") val info: String,//订单信息
    @SerializedName("id") val id: String//订单id
)
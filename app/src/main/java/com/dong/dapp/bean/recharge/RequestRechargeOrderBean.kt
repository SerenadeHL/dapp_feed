package com.dong.dapp.bean.recharge

import com.google.gson.annotations.SerializedName

/**
 * 充值下单请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:49:04
 */
data class RequestRechargeOrderBean(
    @SerializedName("product_id") val productId: String//充值商品id
)
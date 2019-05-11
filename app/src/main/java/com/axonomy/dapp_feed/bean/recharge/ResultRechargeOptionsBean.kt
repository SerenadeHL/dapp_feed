package com.axonomy.dapp_feed.bean.recharge

import com.google.gson.annotations.SerializedName

/**
 * 充值商品结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:51:06
 */
data class ResultRechargeOptionsBean(
    @SerializedName("item") val item: List<ResultRechargeOptionsItemBean>//商品列表
)
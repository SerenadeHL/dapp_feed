package com.axonomy.dapp_feed.bean.coin

import com.google.gson.annotations.SerializedName

/**
 * 金币收益规则说明结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-14 11:57:19
 */
data class ResultRevenueRulesBean(
    @SerializedName("html") val html: String//html文本
)
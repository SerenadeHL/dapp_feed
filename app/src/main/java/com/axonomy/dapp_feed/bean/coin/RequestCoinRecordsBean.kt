package com.axonomy.dapp_feed.bean.coin

import com.google.gson.annotations.SerializedName

/**
 * 金币流水结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-05 14:55:27
 */
data class RequestCoinRecordsBean(
    @SerializedName("protocol") var protocol: Int,//公链类型 tron 0 EOS 1 ETH 2
    @SerializedName("page") var page: Int,//页数
    @SerializedName("page_size") var pageSize: Int//该页条数
)
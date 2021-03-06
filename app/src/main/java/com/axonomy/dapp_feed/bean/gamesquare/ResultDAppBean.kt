package com.axonomy.dapp_feed.bean.gamesquare

import com.google.gson.annotations.SerializedName

/**
 * 游戏广场DApp结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-05 14:42:49
 */
data class ResultDAppBean(
    @SerializedName("items") var items: List<ResultDAppItemBean>?,//Item
    @SerializedName("page_size") var pageSize: Int, //该页条数
    @SerializedName("page") var page: Int,//页数
    @SerializedName("total_count") var totalCount: Int,//总条数
    @SerializedName("page_count") var pageCount: Int//总页数
) {
    fun hasMore() = page + 1 < pageCount
}
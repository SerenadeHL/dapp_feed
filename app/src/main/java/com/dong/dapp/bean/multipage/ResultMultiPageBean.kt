package com.dong.dapp.bean.multipage

import com.dong.dapp.bean.gamesquare.ResultDAppItemBean
import com.google.gson.annotations.SerializedName

/**
 * 请求多页数据结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 10:16:17
 */
data class ResultMultiPageBean<T>(
    @SerializedName("items") var items: List<ResultDAppItemBean>?,//Item
    @SerializedName("page_size") var pageSize: Int, //该页条数
    @SerializedName("page") var page: Int,//页数
    @SerializedName("total_count") var totalCount: Int,//总条数
    @SerializedName("page_count") var pageCount: Int//总页数
)
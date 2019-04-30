package com.dong.dapp.bean.multipage

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 10:16:17
 */
//TODO 泛型擦除导致bug
data class ResultMultiPageBean<T>(
    @SerializedName("items") var items: List<T>?,//Item
    @SerializedName("page_size") var pageSize: Int,//该页条数
    @SerializedName("page") var page: Int,//页数
    @SerializedName("total_count") var totalCount: Int,//总条数
    @SerializedName("page_count") var pageCount: Int//总页数
) : BaseBean()
package com.dong.dapp.bean.wallet

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import java.util.*

data class ProjectListBean(
    @SerializedName("items") var items: ArrayList<Item>?,
    @SerializedName("page_size") var pageSize: Int,
    @SerializedName("page") var page: Int,
    @SerializedName("total_count") var totalCount: Int
) : BaseBean() {
    data class Item(
        @SerializedName("category") var category: String?,
        @SerializedName("pid") var pid: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("intro") var intro: String?,
        @SerializedName("protocols") var protocols: String?,
        @SerializedName("author") var author: String?,
        @SerializedName("logo") var logo: String?,
        @SerializedName("cnt") var count: Int?
    )
}
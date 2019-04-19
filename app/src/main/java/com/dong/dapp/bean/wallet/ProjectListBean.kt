package com.dong.dapp.bean.wallet

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProjectListBean(
    @SerializedName("items") var items: ArrayList<Item>?,
    @SerializedName("page_size") var pageSize: Int,
    @SerializedName("page") var page: Int,
    @SerializedName("page_count") var pageCount: Int,
    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("has_more") var hasMore: Boolean
) : BaseBean() {
    data class Item(
        @SerializedName("category") var category: String?,
        @SerializedName("pid") var pid: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("author") var author: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("logo") var logo: String?
    )
}
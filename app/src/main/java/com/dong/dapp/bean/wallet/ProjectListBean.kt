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
        @SerializedName("id") var id: String?,
        @SerializedName("logo") var logo: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("summary") var summary: String?,
        @SerializedName("users_cnt") var userCount: String?,
        @SerializedName("revenue") var revenue: String?,


        @SerializedName("url") var url: String?
    )
}
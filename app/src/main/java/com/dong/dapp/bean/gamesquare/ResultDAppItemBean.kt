package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

data class ResultDAppItemBean(
    @SerializedName("category") var category: String?,
    @SerializedName("pid") var pid: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("intro") var intro: String?,
    @SerializedName("protocols") var protocols: String?,
    @SerializedName("author") var author: String?,
    @SerializedName("logo") var logo: String?,
    @SerializedName("cnt") var count: Int?
):BaseBean()
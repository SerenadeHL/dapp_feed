package com.dong.dapp.bean.common

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:28:21
 */
data class ResultCommonConfigurationBean(
    @SerializedName("tabs") val tabs: MutableList<ResultCommonConfigurationTabBean>,
    @SerializedName("home_public_header") val homePublicHeader: String,
    @SerializedName("about_url") val aboutUrl: String,
    @SerializedName("menu") val menu: MutableList<MutableList<ResultCommonConfigurationMeOptionBean>>
):BaseBean()
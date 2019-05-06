package com.dong.dapp.bean.common

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:34:05
 */
data class ResultCommonConfigurationMeOptionBean(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("path_ios") val pathIos: String,
    @SerializedName("path_android") val pathAndroid: String
):BaseBean()
package com.dong.dapp.bean.common

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:32:34
 */
data class ResultCommonConfigurationTabBean(
    @SerializedName("name") val name: String,
    @SerializedName("active") val active: String,
    @SerializedName("img") val img: String
) : BaseBean()
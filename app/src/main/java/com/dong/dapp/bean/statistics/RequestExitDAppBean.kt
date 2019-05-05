package com.dong.dapp.bean.statistics

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 18:05:40
 */
data class RequestExitDAppBean(
    @SerializedName("id") var id: String,
    @SerializedName("action") var action: List<Map<String, String>>
) : BaseBean()
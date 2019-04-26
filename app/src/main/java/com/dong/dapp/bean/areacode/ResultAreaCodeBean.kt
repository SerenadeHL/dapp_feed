package com.dong.dapp.bean.areacode

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 19:15:21
 */
data class ResultAreaCodeBean(
    @SerializedName("index") val index: String,
    @SerializedName("items") val items: List<ResultAreaCodeItemBean>
) : BaseBean()
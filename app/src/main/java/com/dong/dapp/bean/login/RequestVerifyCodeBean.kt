package com.dong.dapp.bean.login

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 13:59:52
 */
data class RequestVerifyCodeBean(
    @SerializedName("type") val type: Int,
    @SerializedName("target") val phone: String,
    @SerializedName("area_code") val areaCode: String
) : BaseBean()
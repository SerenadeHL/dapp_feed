package com.axonomy.dapp_feed.bean.dapp

import com.google.gson.annotations.SerializedName

/**
 * 游戏签名请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 14:37:52
 */
data class RequestSignBean(
    @SerializedName("protocol") val protocol: Int,//协议类型
    @SerializedName("message") val message: String,//message
    @SerializedName("type") val type: String//message类型
)
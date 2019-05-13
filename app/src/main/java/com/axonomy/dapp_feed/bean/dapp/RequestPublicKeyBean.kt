package com.axonomy.dapp_feed.bean.dapp

import com.google.gson.annotations.SerializedName

/**
 * 游戏公钥请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 14:37:52
 */
data class RequestPublicKeyBean(
    @SerializedName("protocol") val protocol: Int//协议类型
)
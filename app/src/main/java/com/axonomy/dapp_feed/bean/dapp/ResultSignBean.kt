package com.axonomy.dapp_feed.bean.dapp

import com.google.gson.annotations.SerializedName

/**
 * 游戏签名结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 14:37:52
 */
data class ResultSignBean(
    @SerializedName("signature") var signature: String?,//签名
    @SerializedName("type") var type: String?,//类型
    @SerializedName("message") var message: String?//message
)
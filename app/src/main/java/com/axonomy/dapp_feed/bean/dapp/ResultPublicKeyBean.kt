package com.axonomy.dapp_feed.bean.dapp

import com.google.gson.annotations.SerializedName

/**
 * 游戏公钥结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 14:37:52
 */
data class ResultPublicKeyBean(
    @SerializedName("pub") var publicKey: String?,//公钥
    @SerializedName("account") var account: String?,//账户
    @SerializedName("balance") var balance: Int?,//账户余额
    @SerializedName("frozen") var frozen: Int?//冻结
)

package com.axonomy.dapp_feed.bean.kyc

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 10:32:47
 */
data class ResultKYCSignBean(
    @SerializedName("sign") var sign: String
)
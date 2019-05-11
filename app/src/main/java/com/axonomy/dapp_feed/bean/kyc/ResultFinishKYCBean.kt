package com.axonomy.dapp_feed.bean.kyc

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 16:36:52
 */
data class ResultFinishKYCBean(
    @SerializedName("result_code") var resultCode: Int,
    @SerializedName("result_message") var resultMessage: String?
)
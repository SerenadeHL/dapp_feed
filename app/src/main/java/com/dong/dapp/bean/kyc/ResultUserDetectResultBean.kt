package com.dong.dapp.bean.kyc

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 15:19:38
 */
data class ResultUserDetectResultBean(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String?
)
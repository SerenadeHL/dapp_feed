package com.dong.dapp.bean.kyc

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 10:22:13
 */

data class ResultKYCBizTokenBean(
    @SerializedName("error") var error: String?,
    @SerializedName("biz_token") var bizToken: String?,
    @SerializedName("request_id") var requestId: String,
    @SerializedName("time_used") var timeUsed: Int
)
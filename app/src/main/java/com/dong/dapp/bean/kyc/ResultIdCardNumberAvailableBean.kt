package com.dong.dapp.bean.kyc

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 11:28:39
 */
data class ResultIdCardNumberAvailableBean(
    @SerializedName("isRight") var isRight: Int
)
package com.dong.dapp.bean.kyc

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:27:11
 */
data class RequestIdCardNumberAvailableBean (
    @SerializedName("id_no") val idNumber: String
):BaseBean()
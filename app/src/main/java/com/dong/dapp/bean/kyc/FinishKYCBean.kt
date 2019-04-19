package com.dong.dapp.bean.kyc

import com.dong.dapp.bean.wallet.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 16:36:52
 */
data class FinishKYCBean(
    @SerializedName("result_code") var resultCode: Int,
    @SerializedName("result_message") var resultMessage: String?
) : BaseBean()
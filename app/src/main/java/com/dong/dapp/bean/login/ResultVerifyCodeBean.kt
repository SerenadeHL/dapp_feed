package com.dong.dapp.bean.login

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-12 18:07:27
 */
data class ResultVerifyCodeBean(
    @SerializedName("fp") val fp: String?,
    @SerializedName("registered") val registered: String?
) : BaseBean()
package com.dong.dapp.bean.statistics

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 17:36:02
 */
data class ResultEnterDAppBean(
    @SerializedName("id") val id: String
) : BaseBean()
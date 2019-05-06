package com.dong.dapp.bean.areacode

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 选取区号结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 19:17:25
 */
data class ResultAreaCodeItemBean(
    @SerializedName("code") val code: String,//区号
    @SerializedName("country") val country: String//国家名
) : BaseBean()
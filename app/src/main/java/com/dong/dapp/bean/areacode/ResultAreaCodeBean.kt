package com.dong.dapp.bean.areacode

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 选取区号结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 19:15:21
 */
data class ResultAreaCodeBean(
    @SerializedName("index") val index: String,//首字母
    @SerializedName("items") val items: List<ResultAreaCodeItemBean>//首字母对应的区号列表
) : BaseBean()
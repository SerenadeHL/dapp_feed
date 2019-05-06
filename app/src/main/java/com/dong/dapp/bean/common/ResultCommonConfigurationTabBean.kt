package com.dong.dapp.bean.common

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 主页底部Tab结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:32:34
 */
data class ResultCommonConfigurationTabBean(
    @SerializedName("name") val name: String,//标题
    @SerializedName("active") val active: String,//选中状态下图标地址
    @SerializedName("img") val img: String//未选中状态下图标地址
) : BaseBean()
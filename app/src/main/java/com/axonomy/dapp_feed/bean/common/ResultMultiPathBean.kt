package com.axonomy.dapp_feed.bean.common

import com.google.gson.annotations.SerializedName

/**
 * 多路由结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 20:18:50
 */
data class ResultMultiPathBean(
    @SerializedName("path_ios") val pathIos: String,//ios路由
    @SerializedName("path_android") val pathAndroid: String//android路由
)
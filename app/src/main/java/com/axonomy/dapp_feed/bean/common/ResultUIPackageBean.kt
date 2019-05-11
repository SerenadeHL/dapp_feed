package com.axonomy.dapp_feed.bean.common

import com.google.gson.annotations.SerializedName

/**
 * 皮肤包结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-10 18:36:35
 */
data class ResultUIPackageBean(
    @SerializedName("url_android") val urlAndroid: String,//android端地址
    @SerializedName("url_ios") val urlIos: String,//ios端地址
    @SerializedName("version") val version: Int//版本
)
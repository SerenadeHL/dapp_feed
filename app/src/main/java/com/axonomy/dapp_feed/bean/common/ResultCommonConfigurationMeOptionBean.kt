package com.axonomy.dapp_feed.bean.common

import com.google.gson.annotations.SerializedName

/**
 * 我页面选项结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:34:05
 */
data class ResultCommonConfigurationMeOptionBean(
    @SerializedName("id") val id: Int,//id
    @SerializedName("icon") val icon: String,//图标
    @SerializedName("title") val title: String,//标题
    @SerializedName("style") val style: Int,//style
    @SerializedName("desc") val desc: String,//右箭头左侧文本
    @SerializedName("desc_color") val descColor: String,//右箭头左侧文本颜色
    @SerializedName("path_ios") val pathIos: String,//ios点击跳转
    @SerializedName("path_android") val pathAndroid: String//android点击跳转
)
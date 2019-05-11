package com.axonomy.dapp_feed.bean.common

import com.google.gson.annotations.SerializedName

/**
 * 通用配置结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:28:21
 */
data class ResultCommonConfigurationBean(
    @SerializedName("faq") val faq: String,//FAQ网址
    @SerializedName("user_protocol") val userProtocol: String,//用户协议网址
    @SerializedName("ui_package") val uiPackage: ResultUIPackageBean,//主页底部皮肤包
    @SerializedName("home_public_header") val homePublicHeader: String,//游戏广场未登录时头部背景图
    @SerializedName("floating") val floating: ResultCommonConfigurationFloatingBean,//悬浮按钮
    @SerializedName("about_url") val aboutUrl: String,//关于我们地址
    @SerializedName("menu") val menu: List<List<ResultCommonConfigurationMeOptionBean>>//我页面选项配置
)
package com.axonomy.dapp_feed.bean.login

import com.google.gson.annotations.SerializedName

/**
 * 获取验证码请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 13:59:52
 */
data class RequestVerifyCodeBean(
    @SerializedName("type") val type: Int,//手机号0 邮箱1
    @SerializedName("target") val phone: String,//手机号或邮箱
    @SerializedName("area_code") val areaCode: String//区号
)
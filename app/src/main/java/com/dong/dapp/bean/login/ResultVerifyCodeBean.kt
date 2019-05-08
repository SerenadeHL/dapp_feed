package com.dong.dapp.bean.login

import com.google.gson.annotations.SerializedName

/**
 * 获取验证码结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-12 18:07:27
 */
data class ResultVerifyCodeBean(
    @SerializedName("fp") val fp: String?,//验证码的指纹
    @SerializedName("registered") val registered: String?//注册状态
)
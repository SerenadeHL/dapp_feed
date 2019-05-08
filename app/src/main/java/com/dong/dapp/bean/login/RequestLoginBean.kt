package com.dong.dapp.bean.login

import com.google.gson.annotations.SerializedName

/**
 * 登录请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 14:38:25
 */
data class RequestLoginBean(
    @SerializedName("verification_code") val verifyCode: String,//验证码
    @SerializedName("fp") val fp: String,//验证码的指纹
    @SerializedName("invitation_code") val invitationCode: String//邀请码
)
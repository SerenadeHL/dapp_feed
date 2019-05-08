package com.dong.dapp.bean.login

import com.google.gson.annotations.SerializedName

/**
 * 登录结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-12 18:12:47
 */
data class ResultLoginBean(
    @SerializedName("token") var token: String?//登录标识
)
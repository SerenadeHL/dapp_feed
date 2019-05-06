package com.dong.dapp.bean.common

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 18:41:52
 */
//TODO 添加注释 详情见桌面TODO.txt
data class ResultUpdateInfoBean(
    @SerializedName("app_name") val appName: String,
    @SerializedName("version") val version: String,
    @SerializedName("versionCode") val versionCode: Int,
    @SerializedName("update_type") val updateType: Int,
    @SerializedName("url") val url: String,
    @SerializedName("production") val production: Int,
    @SerializedName("content") val content: String,
    @SerializedName("break_web") val breakWeb: String
) : BaseBean()
package com.axonomy.dapp_feed.bean.update

import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 18:41:52
 */
//TODO 添加注释 详情见桌面TODO.txt
data class ResultUpdateInfoBean(
    @SerializedName("app_name") val appName: String,//App名称
    @SerializedName("version") val version: String,//版本名
    @SerializedName("review_version") val reviewVersion: String,//正在审核中的版本名
    @SerializedName("versionCode") val versionCode: Int,//版本号
    @SerializedName("update_type") val updateType: Int,//强制更新0 显示1 不显示2
    @SerializedName("url") val url: String,//更新地址
    @SerializedName("production") val production: Int,//app审核中0 正式上线>0
    @SerializedName("content") val content: String,//更新弹窗文案
    @SerializedName("break_web") val breakWeb: Int,//是否强制打开网页
    @SerializedName("md5") val md5: String//APKmd5值
)
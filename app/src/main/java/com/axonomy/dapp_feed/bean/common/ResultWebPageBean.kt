package com.axonomy.dapp_feed.bean.common

import com.google.gson.annotations.SerializedName

/**
 * 网页数据结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 20:17:25
 */
data class ResultWebPageBean(
    @SerializedName("inviteUrl") val inviteUrl: ResultMultiPathBean,//邀请好友
    @SerializedName("signUrl") val signUrl: ResultMultiPathBean,//邀请好友
    @SerializedName("aboutUrl") val aboutUrl: ResultMultiPathBean//邀请好友
)
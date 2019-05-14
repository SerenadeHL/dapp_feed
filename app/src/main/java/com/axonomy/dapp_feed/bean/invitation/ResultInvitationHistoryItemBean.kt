package com.axonomy.dapp_feed.bean.invitation

import com.google.gson.annotations.SerializedName

/**
 * 邀请记录结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 19:19:42
 */
data class ResultInvitationHistoryItemBean(
    @SerializedName("id") val id: Int,//用户id
    @SerializedName("status") val status: Int,//用户KYC状态
    @SerializedName("create_time") val createTime: String,//用户注册时间
    @SerializedName("account") val account: String//用户账号
)
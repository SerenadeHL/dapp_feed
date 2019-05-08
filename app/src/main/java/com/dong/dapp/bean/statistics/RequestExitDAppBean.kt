package com.dong.dapp.bean.statistics

import com.google.gson.annotations.SerializedName

/**
 * 退出DApp请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 18:05:40
 */
data class RequestExitDAppBean(
    @SerializedName("id") var id: String,//用户行为id
    @SerializedName("action") var action: List<Map<String, String>>//用户行为
)
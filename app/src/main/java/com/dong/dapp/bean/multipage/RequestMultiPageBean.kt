package com.dong.dapp.bean.multipage

import com.google.gson.annotations.SerializedName

/**
 * 请求多页数据请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 16:30:36
 */
data class RequestMultiPageBean(
    @SerializedName("page") val page: Int,//页数
    @SerializedName("page_size") val pageSize: Int//每页条目数
)
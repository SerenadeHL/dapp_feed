package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 16:30:36
 */
data class RequestDAppListBean(
    @SerializedName("page") val page: Int,//页数
    @SerializedName("page_size") val pageSize: Int//每页条目数
) : BaseBean()
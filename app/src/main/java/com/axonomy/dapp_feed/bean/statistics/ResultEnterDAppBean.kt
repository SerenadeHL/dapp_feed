package com.axonomy.dapp_feed.bean.statistics

import com.google.gson.annotations.SerializedName

/**
 * 进入DApp结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 17:36:02
 */
data class ResultEnterDAppBean(
    @SerializedName("id") val id: String//用户行为id
)
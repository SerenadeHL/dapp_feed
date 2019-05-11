package com.axonomy.dapp_feed.bean.gamesquare

import com.google.gson.annotations.SerializedName

/**
 * 游戏广场公告结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:28:10
 */
data class ResultAnnouncementBean(
    @SerializedName("items") val items: List<ResultAnnouncementItemBean>//游戏广场公告列表
)
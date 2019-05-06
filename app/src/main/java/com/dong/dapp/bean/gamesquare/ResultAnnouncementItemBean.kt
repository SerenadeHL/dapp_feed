package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import com.sunfusheng.marqueeview.IMarqueeItem
import com.sunfusheng.marqueeview.MarqueeView

/**
 * 游戏广场公告结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:28:10
 */
data class ResultAnnouncementItemBean(
    @SerializedName("url") val url: String,//地址
    @SerializedName("id") val id: String,//id
    @SerializedName("title") val title: String//标题
) : BaseBean(), IMarqueeItem {
    override fun marqueeMessage() = title
}
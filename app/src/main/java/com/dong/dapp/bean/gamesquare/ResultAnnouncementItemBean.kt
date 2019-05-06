package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import com.sunfusheng.marqueeview.IMarqueeItem
import com.sunfusheng.marqueeview.MarqueeView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:28:10
 */
data class ResultAnnouncementItemBean(
    @SerializedName("url") val url: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String
) : BaseBean(), IMarqueeItem {
    override fun marqueeMessage() = title
}
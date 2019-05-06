package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:28:10
 */
data class ResultAnnouncementBean(
    @SerializedName("items") val items: List<ResultAnnouncementItemBean>
):BaseBean()
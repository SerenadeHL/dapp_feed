package com.dong.dapp.bean.gamesquare

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 游戏广场DApp结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-05 14:42:49
 */
data class ResultDAppItemBean(
    @SerializedName("category") var category: String?,//类型
    @SerializedName("pid") var pid: String?,//id
    @SerializedName("url") var url: String?,//网址
    @SerializedName("title") var title: String?,//标题
    @SerializedName("intro") var intro: String?,//简介
    @SerializedName("protocols") var protocols: String?,//协议
    @SerializedName("author") var author: String?,//作者
    @SerializedName("logo") var logo: String?,//图标
    @SerializedName("cnt") var count: Int?//玩的人数
) : BaseBean()
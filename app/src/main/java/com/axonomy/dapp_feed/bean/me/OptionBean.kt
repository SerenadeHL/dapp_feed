package com.axonomy.dapp_feed.bean.me

import android.support.annotation.DrawableRes

/**
 * 我页面选项Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 10:44:43
 */
data class OptionBean(
    var icon: String?,//图标
    var title: String?,//标题
    var text: String?,//右箭头左侧文字
    val textColor: String,//右箭头左侧文字颜色
    val showRightArrow: Boolean,//是否显示右箭头
    val showDivider: Boolean,//是否显示分割线
    val path: String?,//点击路由
    val id: Int//id
)
package com.dong.dapp.bean.me

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import com.dong.dapp.bean.BaseBean

/**
 * 我页面选项Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 10:44:43
 */
data class OptionBean(
    @DrawableRes var resId: Int,//图标
    var title: String?,//标题
    var text: String?,//右箭头左侧文字
    @ColorInt var textColor: Int,//右箭头左侧文字颜色
    var showRightArrow: Boolean,//是否显示右箭头
    var showDivider: Boolean//是否显示分割线
) : BaseBean()
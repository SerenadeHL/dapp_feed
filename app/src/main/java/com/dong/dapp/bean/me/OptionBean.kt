package com.dong.dapp.bean.me

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import com.dong.dapp.bean.BaseBean

data class OptionBean(
    @DrawableRes var resId: Int,//图标
    var title: String?,//标题
    var text: String?,//右箭头左侧文字
    @ColorInt var textColor: Int,//右箭头左侧文字颜色
    var showRightArrow: Boolean,//是否显示右箭头
    var showDivider: Boolean//是否显示分割线
) : BaseBean()
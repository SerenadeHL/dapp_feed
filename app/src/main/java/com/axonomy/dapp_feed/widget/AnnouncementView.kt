package com.axonomy.dapp_feed.widget

import android.content.Context
import android.util.AttributeSet
import com.sunfusheng.marqueeview.IMarqueeItem
import com.sunfusheng.marqueeview.MarqueeView

/**
 * 公告跑马灯View
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 16:27:20
 */
class AnnouncementView : MarqueeView<IMarqueeItem> {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

}
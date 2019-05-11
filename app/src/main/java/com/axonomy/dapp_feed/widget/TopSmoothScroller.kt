package com.axonomy.dapp_feed.widget

import android.content.Context
import android.support.v7.widget.LinearSmoothScroller

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 11:57:02
 */
class TopSmoothScroller(context: Context) : LinearSmoothScroller(context) {
    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}
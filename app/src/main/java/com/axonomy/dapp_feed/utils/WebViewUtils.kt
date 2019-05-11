package com.axonomy.dapp_feed.utils

import android.os.Build
import android.view.View

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:11:47
 */
object WebViewUtils {

    /**
     * 让 activity transition 动画过程中可以正常渲染页面
     */
    fun setDrawDuringWindowsAnimating(view: View) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 1 android n以上  & android 4.1以下不存在此问题，无须处理
            return
        }
        // 4.2不存在setDrawDuringWindowsAnimating，需要特殊处理
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            handleDispatchDoneAnimating(view)
            return
        }
        try {
            // 4.3及以上，反射setDrawDuringWindowsAnimating来实现动画过程中渲染
            val rootParent = view.rootView.parent
            val method = rootParent.javaClass
                .getDeclaredMethod("setDrawDuringWindowsAnimating", Boolean::class.javaPrimitiveType!!)
            method.isAccessible = true
            method.invoke(rootParent, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * android4.2可以反射handleDispatchDoneAnimating来解决
     */
    private fun handleDispatchDoneAnimating(paramView: View) {
        try {
            val localViewParent = paramView.rootView.parent
            val localClass = localViewParent.javaClass
            val localMethod = localClass.getDeclaredMethod("handleDispatchDoneAnimating")
            localMethod.isAccessible = true
            localMethod.invoke(localViewParent)
        } catch (localException: Exception) {
            localException.printStackTrace()
        }
    }
}
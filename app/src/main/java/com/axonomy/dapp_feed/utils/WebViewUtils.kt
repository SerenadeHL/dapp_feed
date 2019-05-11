package com.axonomy.dapp_feed.utils

import android.os.Build
import android.view.View
import com.dong.dapp.utils.NetworkUtils
import com.tencent.smtt.sdk.WebSettings
import wendu.dsbridge.DWebView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:11:47
 */
object WebViewUtils {

    fun setSetting(webView: DWebView) {
        webView.settings.apply {
            //设置 缓存模式
            cacheMode = if (NetworkUtils.isNetworkConnected()) {
                WebSettings.LOAD_DEFAULT
            } else {
                WebSettings.LOAD_CACHE_ELSE_NETWORK
            }
            //设置渲染等级为高
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            //开启 DOM storage API 功能
            domStorageEnabled = true
            //开启 database storage API 功能
            databaseEnabled = true
            val cacheDirPath = webView.context.filesDir.absolutePath + webView.context.packageName
            //设置数据库缓存路径
            databasePath = cacheDirPath
            //设置  Application Caches 缓存目录
            setAppCachePath(cacheDirPath)
            //开启 Application Caches 功能
            setAppCacheEnabled(true)
        }
    }

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
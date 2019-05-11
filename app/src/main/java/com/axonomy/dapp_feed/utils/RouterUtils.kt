package com.axonomy.dapp_feed.utils

import android.net.Uri
import android.util.Base64
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 11:35:08
 */
object RouterUtils {
    const val SERVICE_CELL_CLICK = "serviceCellClick"
    const val SERVICE = "service"

    fun route(url: String?, callFunc: (String, Map<String, String?>) -> Unit) {
        if (url == null || url.isEmpty()) return
        val uri = Uri.parse(url)
        when (uri.scheme) {
            "jump" -> {
                val postcard = ARouter.getInstance().build(uri.path)
                uri.queryParameterNames.forEach {
                    var key = it
                    var value = uri.getQueryParameter(key)
                    if (key.endsWith("_base64")) {//如果是以_base64为后缀的，做base64解码处理
                        key = key.substring(0, key.lastIndexOf("_base64"))
                        value = String(Base64.decode(value, Base64.DEFAULT))
                    }
                    postcard.withString(key, value)
                }
                postcard.navigation()
            }
            "func" -> {
                val methodName = uri.host ?: return
                val paramsMap = mutableMapOf<String, String?>()
                uri.queryParameterNames.forEach {
                    var key = it
                    var value = uri.getQueryParameter(key)
                    if (key.endsWith("_base64")) {//如果是以_base64为后缀的，做base64解码处理
                        key = key.substring(0, key.lastIndexOf("_base64"))
                        value = String(Base64.decode(value, Base64.DEFAULT))
                    }
                    paramsMap[key] = value
                }
                callFunc(methodName, paramsMap)
            }
        }
    }
}
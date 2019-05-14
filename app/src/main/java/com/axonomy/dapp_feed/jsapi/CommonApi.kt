package com.axonomy.dapp_feed.jsapi

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.webkit.JavascriptInterface
import anet.channel.util.Utils.context
import com.axonomy.dapp_feed.ui.activity.CommonWebActivity
import com.axonomy.dapp_feed.utils.RouterUtils
import com.axonomy.dapp_feed.utils.ShareUtils
import me.serenadehl.base.extensions.copyToClipboard
import org.json.JSONObject


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 15:01:52
 */
class CommonApi(private val commonWebActivity: CommonWebActivity) {
    companion object {
        const val NAME = "common"
    }

    /**
     * 复制到剪切板
     */
    @JavascriptInterface
    fun copy(msg: Any) {
        msg.toString().copyToClipboard(commonWebActivity)
    }

    /**
     * 分享
     */
    @JavascriptInterface
    fun shareWithData(msg: Any) {
        val jsonObject = JSONObject(msg.toString())
        val title = jsonObject.optString("title")
        val desc = jsonObject.optString("descr")
        val url = jsonObject.optString("url")
        val thumb = jsonObject.optString("thumb")
        ShareUtils.share(commonWebActivity, title, desc, url, thumb)
    }

    /**
     * 标题栏
     */
    @JavascriptInterface
    fun navigationInfo(msg: Any) {
        val jsonObject = JSONObject(msg.toString())
        val title = jsonObject.optString("title")
        val type = jsonObject.optString("type")
        val right = jsonObject.optString("right")
        val link = jsonObject.optString("link")
        commonWebActivity.setTitle(title)

        when (type) {
            "1" -> {//文字
                commonWebActivity.setRightText(right)
                commonWebActivity.setRightTextOnClickListener(View.OnClickListener {
                    RouterUtils.route(link) { _, _ -> }
                })
            }
            "2" -> {//图标
                commonWebActivity.setRightImage(right)
                commonWebActivity.setRightImageOnClickListener(View.OnClickListener {
                    RouterUtils.route(link) { _, _ -> }
                })
            }
        }
    }

    /**
     * 跳转页面
     */
    @JavascriptInterface
    fun jumpWithUrl(msg: Any) {
        RouterUtils.route(msg.toString()) { _, _ -> }
    }
}
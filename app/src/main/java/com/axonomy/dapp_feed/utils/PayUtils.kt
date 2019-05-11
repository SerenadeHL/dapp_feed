package com.axonomy.dapp_feed.utils

import android.app.Activity
import com.alipay.sdk.app.PayTask

/**
 * 支付工具类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:35:19
 */
object PayUtils {
    /**
     * 支付宝支付
     * @param activity
     * @param order 订单信息
     * @param callback 支付完成回调
     */
    fun alipay(activity: Activity, order: String, callback: (Map<String, String>) -> Unit) {
        val payRunnable = Runnable {
            val alipay = PayTask(activity)
            val result = alipay.payV2(order, true)
            activity.runOnUiThread { callback(result) }
        }
        // 必须异步调用
        Thread(payRunnable).start()
    }
}
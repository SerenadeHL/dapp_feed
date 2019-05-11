package com.axonomy.dapp_feed.utils

import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-10 19:34:49
 */
object LocaleUtils {
    /**
     * 是否是中文
     */
    fun isCN() = Locale.getDefault().country == "CN"

}
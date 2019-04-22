package com.dong.dapp.extensions

import com.dong.dapp.bean.wallet.BaseBean
import com.google.gson.Gson

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 16:34:30
 */

inline fun <reified T> String.fromJson(clazz: Class<T>): T? {
    if (!isJson()) return null
    return Gson().fromJson(this, clazz)
}

inline fun BaseBean.toJson(): String {
    return Gson().toJson(this)
}

inline fun List<*>.toJson(): String {
    return Gson().toJson(this)
}

inline fun Map<*,*>.toJson(): String {
    return Gson().toJson(this)
}

inline fun String.isJson(): Boolean {
    return (startsWith("{") && endsWith("}")) or (startsWith("[") && endsWith("]"))
}
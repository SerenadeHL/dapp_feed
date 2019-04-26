package com.dong.dapp.extensions

import com.dong.dapp.bean.BaseBean
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 16:34:30
 */

fun getGson():Gson{
    return GsonBuilder().disableHtmlEscaping().create()
}

inline fun <reified T> String.fromJson(clazz: Class<T>): T? {
    if (!isJson()) return null
    return getGson().fromJson(this, clazz)
}

inline fun <reified T> String.fromJsonToList(): List<T>? {
    if (!isJson()) return null
    return getGson().fromJson(this, object :TypeToken<List<T>>(){}.type)
}

inline fun BaseBean.toJson(): String {
    return getGson().toJson(this)
}

inline fun List<*>.toJson(): String {
    return getGson().toJson(this)
}

inline fun Map<*, *>.toJson(): String {
    return getGson().toJson(this)
}

inline fun String.isJson(): Boolean {
    return (startsWith("{") && endsWith("}")) or (startsWith("[") && endsWith("]"))
}
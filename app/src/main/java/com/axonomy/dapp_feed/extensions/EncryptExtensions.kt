package com.axonomy.dapp_feed.extensions

import android.util.Base64
import com.axonomy.dapp_feed.exception.BaseException
import com.axonomy.dapp_feed.network.BaseResponse
import com.axonomy.dapp_feed.utils.AESUtils
import io.reactivex.Observable
import me.serenadehl.base.extensions.fromJson
import me.serenadehl.base.extensions.isJson
import me.serenadehl.base.extensions.log

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-23 13:22:37
 */

/**
 * 解密
 */
inline fun <reified T> Observable<BaseResponse>.decrypt(): Observable<T?> {
    return flatMap {
        if (it.code != 200) {
            return@flatMap Observable.error<BaseException>(
                BaseException(
                    it.code,
                    it.message
                )
            )
        } else {
            val decode = Base64.decode(it.data, Base64.DEFAULT)
            val decrypt = AESUtils.decrypt(decode)
            "解密数据---------> $decrypt".log()
            val data: T? = decrypt?.fromJson(T::class.java)
            return@flatMap Observable.just(data)
        }
    }.map {
        it as T
    }
}

package com.axonomy.dapp_feed.utils

import android.annotation.SuppressLint
import android.content.Context
import com.axonomy.dapp_feed.network.BaseObserver
import com.axonomy.dapp_feed.network.RequestManager
import me.serenadehl.base.extensions.async


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-10 19:26:10
 */
object OSSUtils {
    const val OSS_BUCKET_PRIVATE_CN = "axonomy"
    const val OSS_BUCKET_PRIVATE_EN = "axonomyen"
    const val OSS_BUCKET_PRIVATE_KYC_CN = "dbaglobal"
    const val OSS_BUCKET_PRIVATE_KYC_EN = "dbaglobalen"

    const val OSS_BUCKET_URL_CN = "https://files.aceport.com/"
    const val OSS_BUCKET_URL_EN = "https://t.dbaglobal.com/"
    const val OSS_BUCKET_URL_KYC_CN = "https://pri.aceport.com/"
    const val OSS_BUCKET_URL_KYC_EN = "https://assets.axonomy.pro/"

    const val OSS_ENDPOINT_CN = "http://oss-cn-hangzhou.aliyuncs.com/"
    const val OSS_ENDPOINT_EN = "http://oss-us-west-1.aliyuncs.com/"

    //TODO AccessDenied
    @SuppressLint("CheckResult")
    fun upload(context: Context, isPrivate: Boolean, data: ByteArray, success: (String) -> Unit) {
        RequestManager.getOSSUploadPermission()
            .flatMap {
                RequestManager.uploadToOSS(context, isPrivate, data, it)
            }
            .async()
            .subscribe {
                success(it ?: "")
            }
    }

}
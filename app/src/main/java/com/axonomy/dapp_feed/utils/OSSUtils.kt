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
    private const val OSS_BUCKET_PRIVATE_CN = "axonomy"
    private const val OSS_BUCKET_PRIVATE_EN = "axonomyen"
    private const val OSS_BUCKET_PRIVATE_KYC_CN = "dbaglobal"
    private const val OSS_BUCKET_PRIVATE_KYC_EN = "dbaglobalen"

    private const val OSS_BUCKET_URL_CN = "https://files.aceport.com/"
    private const val OSS_BUCKET_URL_EN = "https://t.dbaglobal.com/"
    private const val OSS_BUCKET_URL_KYC_CN = "https://pri.aceport.com/"
    private const val OSS_BUCKET_URL_KYC_EN = "https://assets.axonomy.pro/"

    private const val OSS_ENDPOINT_CN = "http://oss-cn-hangzhou.aliyuncs.com/"
    private const val OSS_ENDPOINT_EN = "http://oss-us-west-1.aliyuncs.com/"

    @SuppressLint("CheckResult")
    fun upload(context: Context, isPrivate: Boolean, data: ByteArray, success: (String) -> Unit) {
        val cn = LocaleUtils.isCN()
        val bucketName = if (cn) {
            if (isPrivate) {
                OSS_BUCKET_PRIVATE_KYC_CN
            } else {
                OSS_BUCKET_PRIVATE_CN
            }
        } else {
            if (isPrivate) {
                OSS_BUCKET_PRIVATE_KYC_EN
            } else {
                OSS_BUCKET_PRIVATE_EN
            }
        }
        val endPoint = if (cn) OSS_ENDPOINT_CN else OSS_ENDPOINT_EN

        RequestManager.getOSSUploadPermission()
            .flatMap {
                RequestManager.uploadToOSS(context, bucketName, endPoint, data, it)
            }
            .async()
            .subscribe {
                success(it ?: "")
            }
    }

}
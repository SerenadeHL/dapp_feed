package com.dong.dapp.utils

import android.content.Context
import com.alibaba.sdk.android.oss.ClientException
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.ServiceException
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.dong.dapp.bean.upload.ResultOSSUploadPermissionBean
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.extensions.async
import java.io.File


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

    //TODO 主线程请求网络
    fun upload(context: Context, isPrivate: Boolean, data: ByteArray, success: (String) -> Unit) {
        RequestManager.getOSSUploadPermission()
            .flatMap {
                createObservable(context, isPrivate, data, it)
            }
            .async()
            .subscribe(object : BaseObserver<String?>() {
                override fun next(data: String?) {
                    success(data ?: "")
                }

                override fun error(error: Throwable) {

                }
            })

    }

    private fun createObservable(
        context: Context,
        isPrivate: Boolean,
        data: ByteArray,
        ossUploadPermissionBean: ResultOSSUploadPermissionBean
    ): Observable<String> {
        return Observable.create {
            val cn = LocaleUtils.isCN()
            var bucketName = ""
            var bucketUrl = ""
            if (cn) {
                if (isPrivate) {
                    bucketName = OSS_BUCKET_PRIVATE_KYC_CN
                    bucketUrl = OSS_BUCKET_URL_KYC_CN
                } else {
                    bucketName = OSS_BUCKET_PRIVATE_CN
                    bucketUrl = OSS_BUCKET_URL_CN
                }
            } else {
                if (isPrivate) {
                    bucketName = OSS_BUCKET_PRIVATE_KYC_EN
                    bucketUrl = OSS_BUCKET_URL_KYC_EN
                } else {
                    bucketName = OSS_BUCKET_PRIVATE_EN
                    bucketUrl = OSS_BUCKET_URL_EN
                }
            }
            val endPoint = if (cn) OSS_ENDPOINT_CN else OSS_ENDPOINT_EN
            val fileUrl =
                bucketUrl + ossUploadPermissionBean.catalogue + File.separator + System.currentTimeMillis() + ".png"
            // 构造上传请求。
            val put = PutObjectRequest(bucketName, fileUrl, data)
            val credentialProvider = OSSStsTokenCredentialProvider(
                ossUploadPermissionBean.accessKeyId,
                ossUploadPermissionBean.accessKeySecret,
                ossUploadPermissionBean.securityToken
            )

            try {
                val client = OSSClient(context.applicationContext, endPoint, credentialProvider)
                client.putObject(put)
                it.onNext(fileUrl)
                it.onComplete()
//                client.putObject(put, object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
//                    override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult?) {
//                        it.onNext(fileUrl)
//                        it.onComplete()
//                    }
//
//                    override fun onFailure(
//                        request: PutObjectRequest?,
//                        clientException: ClientException?,
//                        serviceException: ServiceException?
//                    ) {
//                        it.onError(Throwable("上传图片失败"))
//                    }
//
//                })
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(Throwable("上传图片失败"))
            }
        }
    }
}
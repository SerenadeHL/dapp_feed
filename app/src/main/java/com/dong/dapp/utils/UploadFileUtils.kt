package com.dong.dapp.utils

import com.dong.dapp.bean.kyc.UploadFileBean
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.DAppRequest
import me.serenadehl.base.extensions.log
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import okhttp3.MultipartBody


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 14:53:24
 */
object UploadFileUtils {
    fun upload(content: ByteArray, success: (String?) -> Unit) {
        "开始上传文件".log()
        DAppRequest.uploadFile(content)
            .subscribe(object : BaseObserver<UploadFileBean?>() {
                override fun next(data: UploadFileBean?) {
                    "上传成功 : $data".log()
                    success(data?.fileUrl)
                }
            })
    }
}
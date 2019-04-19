package com.dong.dapp.bean.kyc

import com.dong.dapp.bean.wallet.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 14:50:55
 */
data class UploadFileBean(
    @SerializedName("uid") var uid: Int,
    @SerializedName("id") var id: String,
    @SerializedName("file_name") var fineName: String,
    @SerializedName("ext_name") var extName: String,
    @SerializedName("file_url") var fileUrl: String,
    @SerializedName("content_type") var contentType: String
) : BaseBean()
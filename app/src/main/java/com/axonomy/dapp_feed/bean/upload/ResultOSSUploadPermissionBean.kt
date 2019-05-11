package com.axonomy.dapp_feed.bean.upload

import com.google.gson.annotations.SerializedName

/**
 * oss上传权限结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-08 17:34:13
 */
data class ResultOSSUploadPermissionBean(
    @SerializedName("AccessKeyId") val accessKeyId: String,//AccessKeyId
    @SerializedName("AccessKeySecret") val accessKeySecret: String,//AccessKeySecret
    @SerializedName("Expiration") val expiration: String,//时间
    @SerializedName("SecurityToken") val securityToken: String,//stsToken
    @SerializedName("catalogue") val catalogue: String//当前账号可以上传的目录（userid）
)
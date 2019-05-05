package com.dong.dapp.bean.kyc

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 16:43:54
 */
data class RequestKYCInfoBean(
    @SerializedName("biz_token") var bizToken: String?,//bizToken
    @SerializedName("id_info") var idInfo: RequestKYCUserInfoBean?//用户信息
) : BaseBean()
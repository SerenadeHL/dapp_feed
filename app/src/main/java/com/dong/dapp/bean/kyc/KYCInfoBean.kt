package com.dong.dapp.bean.kyc

import android.graphics.PointF
import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import com.megvii.faceidiol.sdk.manager.LegalityStruct

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 16:43:54
 */
data class KYCInfoBean(
    @SerializedName("biz_token") var bizToken: String?,//bizToken
    @SerializedName("id_info") var idInfo: KYCUserInfoBean?//用户信息
) : BaseBean()
package com.dong.dapp.bean.me

import android.os.Parcelable
import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 18:30:33
 */
@Parcelize
data class ResultUserInfoBean(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("kyc_status") val kycStatus: Int,
    @SerializedName("invitation_code") val invitationCode: String,
    @SerializedName("account") val account: String
) : BaseBean(), Parcelable
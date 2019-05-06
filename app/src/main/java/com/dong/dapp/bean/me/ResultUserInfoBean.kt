package com.dong.dapp.bean.me

import android.os.Parcelable
import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 用户信息Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 18:30:33
 */
@Parcelize
data class ResultUserInfoBean(
    @SerializedName("user_id") val userId: Int,//用户id
    @SerializedName("kyc_status") val kycStatus: Int,//KYC状态
    @SerializedName("invitation_code") val invitationCode: String,//邀请码
    @SerializedName("account") val account: String//账号
) : BaseBean(), Parcelable
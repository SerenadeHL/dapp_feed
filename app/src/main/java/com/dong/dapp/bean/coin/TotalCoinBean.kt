package com.dong.dapp.bean.coin

import android.os.Parcelable
import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-25 10:28:11
 */
@Parcelize
data class TotalCoinBean(
    @SerializedName("title") val title: String,
    @SerializedName("time") val time: String,
    @SerializedName("status") val status: String,
    @SerializedName("money") val money: String
) : BaseBean(), Parcelable
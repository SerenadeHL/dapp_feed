package com.axonomy.dapp_feed.bean.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 金币流水结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
@Parcelize
data class ResultCoinRecordsItemBean(
    @SerializedName("ownerAddress") val ownerAddress: String,//转出地址
    @SerializedName("toAddress") val toAddress: String,//转入地址
    @SerializedName("amount") val amount: String,//数量
    @SerializedName("hash") val hash: String,//交易id
    @SerializedName("confirmed") val confirmed: Int,//交易状态 完成 1 进行中0
    @SerializedName("value") val value: String,//交易数目
    @SerializedName("data") val data: String?,//交易名目
    @SerializedName("timestamp") val timeStamp: String//时间
) : Parcelable

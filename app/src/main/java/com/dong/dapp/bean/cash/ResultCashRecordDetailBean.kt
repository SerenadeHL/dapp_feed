package com.dong.dapp.bean.cash

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:08:13
 */
data class ResultCashRecordDetailBean(
    @SerializedName("id") val id: String,
    @SerializedName("cate") val cate: String,
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("changed_type") val changedType: Int,
    @SerializedName("done_status") val doneStatus: Int,
    @SerializedName("create_at_str") val createAtStr: String
) : BaseBean()
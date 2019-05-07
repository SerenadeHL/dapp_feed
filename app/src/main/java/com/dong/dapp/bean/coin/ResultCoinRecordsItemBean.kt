package com.dong.dapp.bean.coin

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 金币流水结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
data class ResultCoinRecordsItemBean(
    @SerializedName("hash") val hash: String,//交易id
    @SerializedName("data") val data: String?,//交易名目
    @SerializedName("value") val value: String,//交易数目
    @SerializedName("timestamp") val time: String,//时间
    @SerializedName("confirmed") val confirmed: Int,//交易状态 完成 1 进行中0


    @SerializedName("id") val id: String,//记录id
    @SerializedName("title") val title: String,//标题
    @SerializedName("amount") val amount: String,//数量
    @SerializedName("changed_type") val changedType: Int,//资金变化类型{-1,1},{减少, 增加}
    @SerializedName("create_at_str") val createAtStr: String,//创建时间
    @SerializedName("done_status") val doneStatus: Int//流水状态{0,1,2,3},{进行中, 已到账, 已完成, 已失败}
) : BaseBean()

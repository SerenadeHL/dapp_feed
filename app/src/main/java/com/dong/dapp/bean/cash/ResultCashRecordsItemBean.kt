package com.dong.dapp.bean.cash

import com.google.gson.annotations.SerializedName

/**
 * 现金流水结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
data class ResultCashRecordsItemBean(
    @SerializedName("id") val id: String,//记录id
    @SerializedName("title") val title: String,//标题
    @SerializedName("amount") val amount: String,//数量
    @SerializedName("changed_type") val changedType: Int,//资金变化类型{-1,1},{减少, 增加}
    @SerializedName("create_at_str") val createAtStr: String,//创建时间
    @SerializedName("done_status") val doneStatus: Int//流水状态{0,1,2,3},{进行中, 已到账, 已完成, 已失败}
)
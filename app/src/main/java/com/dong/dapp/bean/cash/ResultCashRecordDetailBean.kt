package com.dong.dapp.bean.cash

import com.google.gson.annotations.SerializedName

/**
 * 现金流水详情结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 15:08:13
 */
data class ResultCashRecordDetailBean(
    @SerializedName("id") val id: String,//记录id
    @SerializedName("cate") val cate: String,//转账类型
    @SerializedName("title") val title: String,//标题
    @SerializedName("amount") val amount: String,//数量
    @SerializedName("changed_type") val changedType: Int,//资金变化类型{-1,1},{减少, 增加}
    @SerializedName("done_status") val doneStatus: Int,//流水状态{0,1,2,3},{进行中, 已到账, 已完成, 已失败}
    @SerializedName("create_at_str") val createAtStr: String//创建时间
)
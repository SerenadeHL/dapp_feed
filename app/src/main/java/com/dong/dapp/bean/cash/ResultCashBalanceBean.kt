package com.dong.dapp.bean.cash

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 金币余额Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
data class ResultCashBalanceBean(
    @SerializedName("balance") val balance: String,//金币资产
    @SerializedName("total_revenue") val totalRevenue: String,//总收益
    @SerializedName("today_revenue") val todayRevenue: String//今日收益
) : BaseBean()

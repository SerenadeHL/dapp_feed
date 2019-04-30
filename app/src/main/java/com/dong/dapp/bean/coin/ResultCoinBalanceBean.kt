package com.dong.dapp.bean.coin

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 金币余额Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
data class ResultCoinBalanceBean(
    @SerializedName("balance") val balance: String,//金币资产
    @SerializedName("locked") val locked: String,//锁定
    @SerializedName("eval_value") val evalValue: String,//价值人民币
    @SerializedName("transferable") val transferable: String,//可划转
    @SerializedName("today_revenue") val todayRevenue: String//今日收益
) : BaseBean()

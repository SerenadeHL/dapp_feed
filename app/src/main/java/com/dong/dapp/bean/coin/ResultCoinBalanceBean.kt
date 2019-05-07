package com.dong.dapp.bean.coin

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 金币余额结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 9:54:06
 */
data class ResultCoinBalanceBean(
    @SerializedName("id") val id: String,//id
    @SerializedName("balance") val balance: String,//金币资产
    @SerializedName("frozen") val locked: String,//锁定
    @SerializedName("balance_cny") val balanceCNY: String,//价值人民币
    @SerializedName("available") val transferable: String,//可划转
    @SerializedName("todayrevenue") val todayRevenue: String//今日收益
) : BaseBean()

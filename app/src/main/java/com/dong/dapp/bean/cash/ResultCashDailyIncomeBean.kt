package com.dong.dapp.bean.cash

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 现金每日收入结果Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 20:13:00
 */
data class ResultCashDailyIncomeBean(
    @SerializedName("cash") val cash: String//每日收入
) : BaseBean()
package com.dong.dapp.bean.coin

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

/**
 * 金币余额请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 14:25:52
 */
data class RequestCoinBalanceBean(
    @SerializedName("protocol") val protocol: Int//公链类型 tron 0 EOS 1 ETH 2
) : BaseBean()
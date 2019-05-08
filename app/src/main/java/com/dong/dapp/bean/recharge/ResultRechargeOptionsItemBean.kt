package com.dong.dapp.bean.recharge

import com.google.gson.annotations.SerializedName

/**
 * 充值商品结果ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 16:52:16
 */
data class ResultRechargeOptionsItemBean(
    @SerializedName("id") val id: String,//商品id
    @SerializedName("title") val title: String,//商品标题
    @SerializedName("cost") val cost: String//商品价格
)
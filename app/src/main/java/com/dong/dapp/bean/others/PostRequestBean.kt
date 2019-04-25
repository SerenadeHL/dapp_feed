package com.dong.dapp.bean.others

import com.dong.dapp.bean.BaseBean

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-25 21:24:37
 */
data class PostRequestBean(
    val data: String,
    val version: Int,
    val type: Int
) : BaseBean()
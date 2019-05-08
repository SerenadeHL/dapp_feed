package com.dong.dapp.bean.statistics

import com.google.gson.annotations.SerializedName

/**
 * 进入DApp请求Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 17:36:02
 */
data class RequestEnterDAppBean(
    @SerializedName("pid") val pid: String//DApp的pid
)
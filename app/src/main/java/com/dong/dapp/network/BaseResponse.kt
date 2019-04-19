package com.dong.dapp.network

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 20:27:47
 */
data class BaseResponse<T>(val data: T?, val message: String, val code: Int,val errMsg:String?)
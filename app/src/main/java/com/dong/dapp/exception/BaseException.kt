package com.dong.dapp.exception

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 14:35:30
 */

data class BaseException(val code: Int, val msg: String?) : Exception(msg)
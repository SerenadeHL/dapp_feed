package com.axonomy.dapp_feed.constant

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 15:44:03
 */
object KYCStatus {
    //======================KYC状态======================
    const val IN_BLACK_LIST = 0//黑名单
    const val REGISTERED_BUT_UN_LOGIN = 1//注册未登录
    const val UN_VERIFY = 2//未经过KYC
    const val IN_VERIFYING = 3//已提交KYC
    const val VERIFY_PASSED = 4//已通过KYC
}
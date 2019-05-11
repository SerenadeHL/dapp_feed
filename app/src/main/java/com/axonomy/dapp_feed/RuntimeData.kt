package com.axonomy.dapp_feed

import com.axonomy.dapp_feed.bean.common.ResultCommonConfigurationBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:37:31
 */
object RuntimeData {
    //常用配置
    var mResultCommonConfigurationBean: ResultCommonConfigurationBean? = null
    //用户信息
    var mResultUserInfoBean: ResultUserInfoBean? = null

    //今日金币收益
    var mTodayCoinRevenue:String? = null
    //今日现金收益
    var mTodayCashRevenue:String? = null
}
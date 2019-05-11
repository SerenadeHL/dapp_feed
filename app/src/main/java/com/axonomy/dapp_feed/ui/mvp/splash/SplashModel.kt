package com.axonomy.dapp_feed.ui.mvp.splash

import com.axonomy.dapp_feed.bean.common.ResultCommonConfigurationBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:40:09
 */

class SplashModel : MVPBaseModel(), ISplashModel {

    /**
     * 获取通用配置
     */
    override fun getCommonConfiguration(): Observable<ResultCommonConfigurationBean?> {
        return RequestManager.getCommonConfiguration()
    }

    /**
     * 获取用户信息
     */
    override fun getUserInfo(): Observable<ResultUserInfoBean?> {
        return RequestManager.getUserInfo()
    }
}
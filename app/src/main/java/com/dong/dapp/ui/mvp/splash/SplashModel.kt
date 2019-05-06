package com.dong.dapp.ui.mvp.splash

import com.dong.dapp.bean.common.ResultCommonConfigurationBean
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:40:09
 */

class SplashModel :MVPBaseModel(),ISplashModel{

    override fun getCommonConfiguration(): Observable<ResultCommonConfigurationBean?> {
        return RequestManager.getCommonConfiguration()
    }
}
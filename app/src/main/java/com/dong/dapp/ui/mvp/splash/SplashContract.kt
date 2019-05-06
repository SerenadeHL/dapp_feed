package com.dong.dapp.ui.mvp.splash

import com.dong.dapp.bean.common.ResultCommonConfigurationBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 10:40:09
 */
interface ISplashView : IBaseView {
    fun getCommonConfigurationSuccess(data: ResultCommonConfigurationBean?)

    fun getCommonConfigurationFailed()
}

interface ISplashPresenter : IBasePresenter {
    /**
     * 获取通用配置
     */
    fun getCommonConfiguration()
}

interface ISplashModel : IBaseModel {
    /**
     * 获取通用配置
     */
    fun getCommonConfiguration(): Observable<ResultCommonConfigurationBean?>
}

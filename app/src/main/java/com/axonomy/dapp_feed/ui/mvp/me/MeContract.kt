package com.axonomy.dapp_feed.ui.mvp.me

import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
interface IMeView : IBaseView {
    fun getUserInfoSuccess(data: ResultUserInfoBean?)

    fun getUserInfoFailed()
}

interface IMePresenter : IBasePresenter {
    /**
     * 获取用户信息
     */
    fun getUserInfo()
}

interface IMeModel : IBaseModel {
    /**
     * 获取用户信息
     */
    fun getUserInfo(): Observable<ResultUserInfoBean?>
}

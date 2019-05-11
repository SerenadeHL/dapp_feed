package com.axonomy.dapp_feed.ui.mvp.main

import com.axonomy.dapp_feed.bean.update.ResultUpdateInfoBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 10:02:56
 */
interface IMainView : IBaseView {
    fun getUpdateInfoSuccess(data: ResultUpdateInfoBean?)

    fun getUpdateInfoFailed()
}

interface IMainPresenter : IBasePresenter {
    /**
     * 获取版本更新信息
     */
    fun getUpdateInfo()
}

interface IMainModel : IBaseModel {
    /**
     * 获取版本更新信息
     */
    fun getUpdateInfo(): Observable<ResultUpdateInfoBean?>
}
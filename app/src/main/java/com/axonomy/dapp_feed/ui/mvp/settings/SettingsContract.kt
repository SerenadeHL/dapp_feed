package com.axonomy.dapp_feed.ui.mvp.settings

import com.axonomy.dapp_feed.bean.update.ResultUpdateInfoBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 15:41:02
 */
interface ISettingsView : IBaseView {
    fun getUpdateInfoSuccess(data: ResultUpdateInfoBean?)

    fun getUpdateInfoFailed()
}

interface ISettingsPresenter : IBasePresenter {
    /**
     * 获取版本更新信息
     */
    fun getUpdateInfo()
}

interface ISettingsModel : IBaseModel {
    /**
     * 获取版本更新信息
     */
    fun getUpdateInfo(): Observable<ResultUpdateInfoBean?>
}
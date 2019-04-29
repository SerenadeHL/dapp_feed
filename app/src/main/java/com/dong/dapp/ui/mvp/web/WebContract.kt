package com.dong.dapp.ui.mvp.web

import com.dong.dapp.bean.statistics.ResultEnterDAppBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
interface IWebView : IBaseView {
    fun enterDAppSuccess(data: ResultEnterDAppBean?)

    fun enterDAppFailed()
}

interface IWebPresenter : IBasePresenter {
    fun enterDApp(pid: String)

    fun exitDApp(id: String, action: List<Map<String, String>>)
}

interface IWebModel : IBaseModel {
    fun enterDApp(pid: String): Observable<ResultEnterDAppBean?>

    fun exitDApp(id: String, action: List<Map<String, String>>): Observable<BaseResponse>
}

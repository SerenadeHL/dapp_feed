package com.axonomy.dapp_feed.ui.mvp.web

import com.axonomy.dapp_feed.bean.statistics.ResultEnterDAppBean
import com.axonomy.dapp_feed.network.BaseResponse
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
interface IDAppWebView : IBaseView {
    fun enterDAppSuccess(data: ResultEnterDAppBean?)

    fun enterDAppFailed()
}

interface IDAppWebPresenter : IBasePresenter {
    fun enterDApp(pid: String)

    fun exitDApp(id: String, action: List<Map<String, String>>)
}

interface IDAppWebModel : IBaseModel {
    fun enterDApp(pid: String): Observable<ResultEnterDAppBean?>

    fun exitDApp(id: String, action: List<Map<String, String>>): Observable<BaseResponse?>
}

package com.dong.dapp.ui.mvp.web

import com.dong.dapp.bean.statistics.ResultEnterDAppBean
import com.dong.dapp.network.BaseResponse
import com.dong.dapp.network.DAppRequest
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
class WebModel : MVPBaseModel(), IWebModel {

    override fun enterDApp(pid: String): Observable<ResultEnterDAppBean?> {
        return DAppRequest.enterDApp(pid)
    }

    override fun exitDApp(id: String, action: List<Map<String, String>>): Observable<BaseResponse> {
        return DAppRequest.exitDApp(id, action)
    }
}

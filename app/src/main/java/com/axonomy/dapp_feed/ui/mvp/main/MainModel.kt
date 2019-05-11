package com.axonomy.dapp_feed.ui.mvp.main

import com.axonomy.dapp_feed.bean.update.ResultUpdateInfoBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 10:06:01
 */
class MainModel : MVPBaseModel(), IMainModel{

    override fun getUpdateInfo(): Observable<ResultUpdateInfoBean?> {
        return RequestManager.getUpdateInfo()
    }
}
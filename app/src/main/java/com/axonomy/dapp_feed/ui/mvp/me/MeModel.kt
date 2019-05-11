package com.axonomy.dapp_feed.ui.mvp.me

import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
class MeModel : MVPBaseModel(), IMeModel {

    override fun getUserInfo(): Observable<ResultUserInfoBean?> {
        return RequestManager.getUserInfo()
    }
}

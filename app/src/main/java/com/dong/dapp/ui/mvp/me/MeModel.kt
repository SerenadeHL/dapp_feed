package com.dong.dapp.ui.mvp.me

import com.dong.dapp.bean.me.ResultUserInfoBean
import com.dong.dapp.network.RequestManager
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

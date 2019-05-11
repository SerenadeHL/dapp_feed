package com.axonomy.dapp_feed.ui.mvp.chooseareacode

import com.axonomy.dapp_feed.bean.areacode.ResultAreaCodeBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
class ChooseAreaCodeModel : MVPBaseModel(), IChooseAreaCodeModel {
    override fun getAreaCode(): Observable<List<ResultAreaCodeBean>?> {
        return RequestManager.getAreaCode()
    }

}

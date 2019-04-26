package com.dong.dapp.ui.mvp.chooseareacode

import com.dong.dapp.bean.areacode.ResultAreaCodeBean
import com.dong.dapp.network.DAppRequest
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
        return DAppRequest.getAreaCode()
    }

}

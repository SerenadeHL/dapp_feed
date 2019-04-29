package com.dong.dapp.ui.mvp.chooseareacode

import com.dong.dapp.bean.areacode.ResultAreaCodeBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
interface IChooseAreaCodeView : IBaseView{
    fun setAreaCode(data :List<ResultAreaCodeBean>)
}

interface IChooseAreaCodePresenter : IBasePresenter{
    fun getAreaCode()
}

interface IChooseAreaCodeModel : IBaseModel {
    fun getAreaCode(): Observable<List<ResultAreaCodeBean>?>
}

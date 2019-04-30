package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.multipage.ResultMultiPageBean
import com.dong.dapp.bean.gamesquare.ResultDAppItemBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
interface IGameSquareView : IBaseView {
    fun getDAppListSuccess(data: ResultMultiPageBean<ResultDAppItemBean>?)

    fun getDAppListFailed()
}

interface IGameSquarePresenter : IBasePresenter {
    fun getDAppList(page: Int, pageSize: Int)
}

interface IGameSquareModel : IBaseModel {
    fun getDAppList(page: Int, pageSize: Int): Observable<ResultMultiPageBean<ResultDAppItemBean>?>
}

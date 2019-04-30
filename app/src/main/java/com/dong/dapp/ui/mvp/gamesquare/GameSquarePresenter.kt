package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.multipage.ResultMultiPageBean
import com.dong.dapp.bean.gamesquare.ResultDAppItemBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquarePresenter : MVPBasePresenter<IGameSquareView, IGameSquareModel>(), IGameSquarePresenter {

    override fun createModel() = GameSquareModel()

    override fun getDAppList(page: Int, pageSize: Int) {
        mModel.getDAppList(page, pageSize)
            .subscribe(object : BaseObserver<ResultMultiPageBean<ResultDAppItemBean>?>() {
                override fun next(data: ResultMultiPageBean<ResultDAppItemBean>?) {
                    mView.get()?.getDAppListSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getDAppListFailed()
                }
            })
    }
}

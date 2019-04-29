package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.gamesquare.ResultDAppListBean
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
            .subscribe(object : BaseObserver<ResultDAppListBean?>() {
                override fun next(data: ResultDAppListBean?) {
                    mView.get()?.getDAppListSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getDAppListFailed()
                }
            })
    }
}

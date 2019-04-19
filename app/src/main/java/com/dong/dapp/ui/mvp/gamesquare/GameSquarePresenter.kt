package com.dong.dapp.ui.mvp.gamesquare

import me.serenadehl.base.base.mvpbase.MVPBasePresenter

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquarePresenter : MVPBasePresenter<IGameSquareView, IGameSquareModel>(), IGameSquarePresenter {

    override fun createModel() = GameSquareModel()

}

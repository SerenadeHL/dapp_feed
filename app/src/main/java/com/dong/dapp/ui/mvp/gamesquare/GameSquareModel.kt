package com.dong.dapp.ui.mvp.gamesquare

import com.dong.dapp.bean.gamesquare.ResultDAppBean
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquareModel : MVPBaseModel(), IGameSquareModel {

    override fun getDAppList(page: Int, pageSize: Int): Observable<ResultDAppBean?> {
        return RequestManager.getDAppList(page, pageSize)
    }
}

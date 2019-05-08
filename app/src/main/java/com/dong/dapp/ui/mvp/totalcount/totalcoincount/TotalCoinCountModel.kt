package com.dong.dapp.ui.mvp.totalcount.totalcoincount

import com.dong.dapp.bean.coin.ResultCoinBalanceBean
import com.dong.dapp.bean.coin.ResultCoinRecordsBean
import com.dong.dapp.constant.Constant
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountModel : MVPBaseModel(), ITotalCoinCountModel {

    override fun getCoinBalance(): Observable<ResultCoinBalanceBean?> {
        return RequestManager.getCoinBalance(Constant.TRON)
    }

    override fun getCoinRecords(page: Int, pageSize: Int): Observable<ResultCoinRecordsBean?> {
        return RequestManager.getCoinRecords(Constant.TRON, page, pageSize)
    }

}

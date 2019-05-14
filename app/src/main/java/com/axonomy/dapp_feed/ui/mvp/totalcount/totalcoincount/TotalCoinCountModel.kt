package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcoincount

import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinRecordsBean
import com.axonomy.dapp_feed.constant.Constant
import com.axonomy.dapp_feed.constant.Protocol
import com.axonomy.dapp_feed.network.RequestManager
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
        return RequestManager.getCoinBalance(Protocol.TRON)
    }

    override fun getCoinRecords(page: Int, pageSize: Int): Observable<ResultCoinRecordsBean?> {
        return RequestManager.getCoinRecords(Protocol.TRON, page, pageSize)
    }
}

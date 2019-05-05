package com.dong.dapp.ui.mvp.totalcount.totalcashcount

import com.dong.dapp.bean.cash.ResultCashBalanceBean
import com.dong.dapp.bean.cash.ResultCashRecordsBean
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
class TotalCashCountModel : MVPBaseModel(), ITotalCashCountModel {
    override fun getCashBalance(): Observable<ResultCashBalanceBean?> {
        return RequestManager.getCashBalance()
    }

    override fun getCashRecords(page: Int, pageSize: Int): Observable<ResultCashRecordsBean?> {
        return RequestManager.getCashRecords(page, pageSize)
    }

}

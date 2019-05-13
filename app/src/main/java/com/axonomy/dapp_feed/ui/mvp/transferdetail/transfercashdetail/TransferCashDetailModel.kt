package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercashdetail

import com.axonomy.dapp_feed.bean.cash.ResultCashRecordDetailBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 12:24:10
 */
class TransferCashDetailModel : MVPBaseModel(),
    ITransferCashDetailModel {

    override fun getCashRecordDetail(recordId: String): Observable<ResultCashRecordDetailBean?> {
        return RequestManager.getCashRecordDetail(recordId)
    }
}
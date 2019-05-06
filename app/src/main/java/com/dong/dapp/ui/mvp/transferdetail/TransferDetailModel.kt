package com.dong.dapp.ui.mvp.transferdetail

import com.dong.dapp.bean.cash.ResultCashRecordDetailBean
import com.dong.dapp.network.RequestManager
import com.dong.dapp.network.RetrofitHelper
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 12:24:10
 */
class TransferDetailModel : MVPBaseModel(), ITransferDetailModel {

    override fun getCashRecordDetail(recordId: String): Observable<ResultCashRecordDetailBean?> {
        return RequestManager.getCashRecordDetail(recordId)
    }
}
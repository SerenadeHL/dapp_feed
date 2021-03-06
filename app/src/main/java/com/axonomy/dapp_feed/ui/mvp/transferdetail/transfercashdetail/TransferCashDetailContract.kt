package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercashdetail

import com.axonomy.dapp_feed.bean.cash.ResultCashRecordDetailBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 13:20:47
 */

interface ITransferCashDetailView : IBaseView {
    fun getCashRecordDetailSuccess(data: ResultCashRecordDetailBean?)

    fun getCashRecordDetailFailed()
}

interface ITransferCashDetailPresenter : IBasePresenter {
    /**
     * 获取现金流水详情
     * @param recordId 流水id
     */
    fun getCashRecordDetail(recordId: String)
}

interface ITransferCashDetailModel : IBaseModel {
    /**
     * 获取现金流水详情
     * @param recordId 流水id
     */
    fun getCashRecordDetail(recordId: String): Observable<ResultCashRecordDetailBean?>
}

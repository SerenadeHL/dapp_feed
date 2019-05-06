package com.dong.dapp.ui.mvp.transferdetail

import com.dong.dapp.bean.cash.ResultCashRecordDetailBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 13:20:47
 */

interface ITransferDetailView : IBaseView {
    fun getCashRecordDetailSuccess(data: ResultCashRecordDetailBean?)

    fun getCashRecordDetailFailed()
}

interface ITransferDetailPresenter : IBasePresenter {
    /**
     * 获取现金流水详情
     * @param recordId 流水id
     */
    fun getCashRecordDetail(recordId: String)
}

interface ITransferDetailModel : IBaseModel {
    /**
     * 获取现金流水详情
     * @param recordId 流水id
     */
    fun getCashRecordDetail(recordId: String): Observable<ResultCashRecordDetailBean?>
}

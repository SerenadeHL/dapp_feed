package com.dong.dapp.ui.mvp.totalcount.totalcashcount

import com.dong.dapp.bean.cash.ResultCashBalanceBean
import com.dong.dapp.bean.cash.ResultCashRecordsBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 *  现金资产总额页面
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
interface ITotalCashCountView : IBaseView{
    fun getCashBalanceSuccess(data: ResultCashBalanceBean?)

    fun getCashBalanceFailed()

    fun getCashRecordsSuccess(data: ResultCashRecordsBean?, refresh: Boolean)

    fun getCashRecordsFailed()
}

interface ITotalCashCountPresenter : IBasePresenter{
    /**
     * 获取现金资产
     */
    fun getCashBalance()

    /**
     * 获取现金流水
     * @param page 页数
     * @param pageSize 条数
     */
    fun getCashRecords(page: Int, pageSize: Int, refresh: Boolean)
}

interface ITotalCashCountModel : IBaseModel{
    /**
     * 获取现金资产
     */
    fun getCashBalance(): Observable<ResultCashBalanceBean?>

    /**
     * 获取现金流水
     * @param page 页数
     * @param pageSize 条数
     */
    fun getCashRecords(page: Int, pageSize: Int): Observable<ResultCashRecordsBean?>
}


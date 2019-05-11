package com.axonomy.dapp_feed.ui.mvp.kyc

import com.axonomy.dapp_feed.bean.kyc.ResultIdCardNumberAvailableBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:47:11
 */
interface IKYCView : IBaseView {
    fun checkIdCarNumberSuccess(bean: ResultIdCardNumberAvailableBean?)

    fun checkIdCarNumberFailed()
}

interface IKYCPresenter : IBasePresenter {
    /**
     * 判断身份证号是否可用
     * @param idNumber 身份证号
     */
    fun checkIdCarNumber(idNumber: String)
}

interface IKYCModel : IBaseModel {
    /**
     * 判断身份证号是否可用
     * @param idNumber 身份证号
     */
    fun checkIdCarNumber(idNumber: String): Observable<ResultIdCardNumberAvailableBean?>
}
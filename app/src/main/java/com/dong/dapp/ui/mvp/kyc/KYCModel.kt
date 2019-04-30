package com.dong.dapp.ui.mvp.kyc

import com.dong.dapp.bean.kyc.ResultIdCardNumberAvailableBean
import com.dong.dapp.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 16:49:56
 */
class KYCModel : MVPBaseModel(), IKYCModel {

    override fun checkIdCarNumber(idNumber: String): Observable<ResultIdCardNumberAvailableBean?> {
        return RequestManager.isIdCardNumberAvailable(idNumber)
    }
}
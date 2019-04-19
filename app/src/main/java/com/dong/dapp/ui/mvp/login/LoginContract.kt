package com.dong.dapp.ui.mvp.login

import com.dong.dapp.bean.wallet.VerifyCodeBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 10:57:50
 */
interface ILoginView : IBaseView {
    /**
     * 获取验证码完成
     * @param success 是否成功
     */
    fun getVerifyCodeFinished(success: Boolean)
}

interface ILoginPresenter : IBasePresenter {
    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String)
}

interface ILoginModel : IBaseModel {
    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String): Observable<BaseResponse<VerifyCodeBean?>>
}

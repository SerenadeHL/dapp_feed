package com.dong.dapp.ui.mvp.login

import com.dong.dapp.bean.login.ResultLoginBean
import com.dong.dapp.bean.login.ResultVerifyCodeBean
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
     * 获取验证码成功
     */
    fun getVerifyCodeSuccess(verifyCodeBean: ResultVerifyCodeBean?)

    /**
     * 获取验证码失败
     */
    fun getVerifyCodeFailed()

    /**
     * 登录成功
     */
    fun loginSuccess(loginBean: ResultLoginBean?)

    /**
     * 登录失败
     */
    fun loginFailed()
}

interface ILoginPresenter : IBasePresenter {
    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String)

    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param invitationCode 邀请码
     */
    fun login(verifyCode: String, fp: String, invitationCode: String)
}

interface ILoginModel : IBaseModel {
    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    fun getVerifyCode(phone: String, areaCode: String): Observable<ResultVerifyCodeBean?>

    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param invitationCode 邀请码
     */
    fun login(verifyCode: String, fp: String, invitationCode: String): Observable<ResultLoginBean?>
}

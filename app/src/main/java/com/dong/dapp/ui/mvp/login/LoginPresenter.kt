package com.dong.dapp.ui.mvp.login

import com.dong.dapp.bean.login.ResultLoginBean
import com.dong.dapp.bean.login.ResultVerifyCodeBean
import com.dong.dapp.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.log

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 10:59:20
 */
class LoginPresenter : MVPBasePresenter<ILoginView, ILoginModel>(), ILoginPresenter {

    override fun createModel() = LoginModel()

    override fun getVerifyCode(phone: String, areaCode: String) {
        mModel.getVerifyCode(phone, areaCode)
            .subscribe(object : BaseObserver<ResultVerifyCodeBean?>() {
                override fun next(data: ResultVerifyCodeBean?) {
                    "获取验证码成功,data=$data".log()
                    mView.get()?.getVerifyCodeSuccess(data)
                }

                override fun error(error: Throwable) {
                    "获取验证码失败".log()
                    mView.get()?.getVerifyCodeFailed()
                }
            })
    }

    override fun login(verifyCode: String, fp: String, invitationCode: String) {
        mModel.login(verifyCode, fp, invitationCode)
            .subscribe(object : BaseObserver<ResultLoginBean?>() {
                override fun next(data: ResultLoginBean?) {
                    "登录成功,data=$data".log()
                    mView.get()?.loginSuccess(data)
                }

                override fun error(error: Throwable) {
                    "登录失败".log()
                    mView.get()?.loginFailed()
                }
            })
    }
}
package com.axonomy.dapp_feed.ui.mvp.login

import com.axonomy.dapp_feed.bean.login.ResultLoginBean
import com.axonomy.dapp_feed.bean.login.ResultVerifyCodeBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.network.BaseObserver
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
                    mView.get()?.getVerifyCodeSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getVerifyCodeFailed()
                }
            })
    }

    override fun login(verifyCode: String, fp: String, invitationCode: String) {
        mModel.login(verifyCode, fp, invitationCode)
            .subscribe(object : BaseObserver<ResultLoginBean?>() {
                override fun next(data: ResultLoginBean?) {
                    mView.get()?.loginSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.loginFailed()
                }
            })
    }

    override fun getUserInfo() {
        mModel.getUserInfo()
            .subscribe(object :BaseObserver<ResultUserInfoBean?>(){
                override fun next(data: ResultUserInfoBean?) {
                    mView.get()?.getUserInfoSuccess(data)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getUserInfoFailed()
                }
            })
    }
}
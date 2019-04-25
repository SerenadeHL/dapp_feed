package com.dong.dapp.ui.mvp.login

import com.dong.dapp.bean.login.VerifyCodeBean
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
            .subscribe(object : BaseObserver<VerifyCodeBean?>() {
                override fun next(data: VerifyCodeBean?) {
                    "获取验证码成功,data=$data".log()
                    mView.get()?.getVerifyCodeFinished(true)
                }

                override fun error(error: Throwable) {
                    super.error(error)
                    "获取验证码失败".log()
                    mView.get()?.getVerifyCodeFinished(false)
                }
            })
    }
}
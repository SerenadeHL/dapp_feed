package com.dong.dapp.ui.mvp.login

import android.os.Bundle
import com.dong.dapp.R
import me.serenadehl.base.base.mvpbase.MVPBaseActivity

/**
 * 登录页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 10:57:09
 */
class LoginActivity : MVPBaseActivity<ILoginPresenter>(), ILoginView {

    override fun layout() = R.layout.activity_login

    override fun createPresenter() = LoginPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

    }

    override fun getVerifyCodeFinished(success: Boolean) {
        if (success) {//获取验证码成功

        } else {//获取验证码失败

        }
    }
}

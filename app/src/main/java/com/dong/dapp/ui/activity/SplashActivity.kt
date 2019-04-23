package com.dong.dapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.dong.dapp.R
import io.reactivex.Observable
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.startActivity
import java.util.concurrent.TimeUnit


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 13:11:20
 */
class SplashActivity : BaseActivity() {

    override fun layout() = R.layout.activity_splash

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Observable.timer(1, TimeUnit.SECONDS)
            .subscribe {
                startActivity<MainActivity>()
                finish()
            }
    }

}
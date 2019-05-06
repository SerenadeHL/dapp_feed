package com.dong.dapp.ui.mvp.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import com.dong.dapp.R
import com.dong.dapp.RuntimeData
import com.dong.dapp.bean.common.ResultCommonConfigurationBean
import com.dong.dapp.ui.activity.MainActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.startActivity
import me.serenadehl.base.utils.app.AppManager
import java.util.concurrent.TimeUnit


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 13:11:20
 */
class SplashActivity : MVPBaseActivity<ISplashPresenter>(), ISplashView {

    override fun createPresenter() = SplashPresenter()

    override fun layout() = R.layout.activity_splash

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //获取必要权限
        RxPermissions(this@SplashActivity)
            .request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .subscribe { granted ->
                if (granted) {
                    loadData()
                } else {
                    AppManager.instance.exitApp()
                }
            }
    }

    private fun loadData() {
        mPresenter.getCommonConfiguration()
    }

    override fun getCommonConfigurationSuccess(data: ResultCommonConfigurationBean?) {
        "getCommonConfigurationSuccess--------> $data".log()
        RuntimeData.mResultCommonConfigurationBean = data
        startActivity<MainActivity>()
        finish()
    }

    override fun getCommonConfigurationFailed() {

    }

}
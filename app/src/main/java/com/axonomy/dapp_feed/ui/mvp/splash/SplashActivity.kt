package com.axonomy.dapp_feed.ui.mvp.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.RuntimeData
import com.axonomy.dapp_feed.bean.common.ResultCommonConfigurationBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.tbruyelle.rxpermissions2.RxPermissions
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.app.AppManager

/**
 * 启动页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 13:11:20
 */
@Route(path = Router.SPLASH_ACTIVITY)
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
                    AppManager.exitApp()
                }
            }
    }

    private fun loadData() {
        mPresenter.getCommonConfiguration()
        mPresenter.getUserInfo()
    }

    override fun getCommonConfigurationSuccess(data: ResultCommonConfigurationBean?) {
        "getCommonConfigurationSuccess-------> $data".log()
        RuntimeData.mResultCommonConfigurationBean = data

        ARouter.getInstance()
            .build(Router.MAIN_ACTIVITY)
            .navigation()
        finish()
    }

    override fun getCommonConfigurationFailed() {
        "getCommonConfigurationFailed------->".log()
    }

    override fun getUserInfoSuccess(data: ResultUserInfoBean?) {
        "getUserInfoSuccess-------> $data".log()
        RuntimeData.mResultUserInfoBean = data
    }

    override fun getUserInfoFailed() {
        "getUserInfoFailed------->".log()
    }
}
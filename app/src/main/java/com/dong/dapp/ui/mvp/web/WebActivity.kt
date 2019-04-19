package com.dong.dapp.ui.mvp.web

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.widget.LinearLayout
import com.dong.dapp.DAppApplication
import com.dong.dapp.R
import com.dong.dapp.bean.wallet.UserInfoBean
import com.dong.dapp.extensions.save
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.DAppRequest
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.startActivity
import wendu.dsbridge.DWebView

/**
 * Web容器
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
class WebActivity : MVPBaseActivity<IWebPresenter>(), IWebView {
    private lateinit var mUrl: String
    private lateinit var mWebView: DWebView

    companion object {
        fun start(activity: Activity, url: String) {
            activity.startActivity<WebActivity>("url" to url)
        }

        fun start(fragment: Fragment, url: String) {
            fragment.startActivity<WebActivity>("url" to url)
        }
    }

    override fun layout() = R.layout.activity_web

    override fun createPresenter() = WebPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mUrl = intent.getStringExtra("url")

        mWebView = (application as DAppApplication).getWebView()
        mRootView.addView(
            mWebView, LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        )
        mWebView.loadUrl(mUrl)

        DAppRequest.getTronUserInfo()
            .subscribe(object : BaseObserver<UserInfoBean?>() {
                override fun next(data: UserInfoBean?) {
                    data?.save()
                }

                override fun error(error: Throwable) {
                    error.printStackTrace()
                }

                override fun complete() {
                    "==onComplete==".log()
                }
            })
    }

    override fun onDestroy() {
        mWebView.stopLoading()
        mWebView.loadUrl("about:blank")
        mWebView.clearHistory()
        mRootView.removeView(mWebView)
        super.onDestroy()
    }

}
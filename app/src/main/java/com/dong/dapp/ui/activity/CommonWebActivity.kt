package com.dong.dapp.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dong.dapp.R
import com.dong.dapp.constant.Router
import com.dong.dapp.constant.RouterParams
import com.squareup.leakcanary.RefWatcher
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.log
import java.lang.Exception

/**
 * Web网页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 13:17:25
 */
@Route(path = Router.COMMON_WEB_ACTIVITY)
class CommonWebActivity : BaseActivity() {

    @JvmField
    @Autowired(name = RouterParams.URL)
    var mUrl: String? = null

    private val mC2 by lazy { ContextCompat.getColor(this@CommonWebActivity, R.color.C2) }

    override fun layout() = R.layout.activity_common_web

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.personal_info)
        cl_title.setBackgroundColor(mC2)

        "mUrl----------->$mUrl".log()

        //TODO 配置WebView
        wv_web.webViewClient = WebViewClient()
        wv_web.webChromeClient = WebChromeClient()

        wv_web.loadUrl(mUrl)
    }

    override fun onDestroy() {
        try {
            (wv_web.parent as ViewGroup).removeView(wv_web)
            wv_web.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

}
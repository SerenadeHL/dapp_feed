package com.dong.dapp

import android.content.Context
import android.support.multidex.MultiDex
import android.view.ViewGroup
import com.dong.dapp.jsapi.TronPayApi
import com.dong.dapp.ui.mvp.web.WebActivity
import com.dong.dapp.utils.JsResUtils
import com.dong.dapp.utils.NetworkUtils
import com.megvii.meglive_sdk.sdk.manager.FaceIdManager
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins
import me.serenadehl.base.BaseApplication
import me.serenadehl.base.utils.app.AppManager
import wendu.dsbridge.DWebView

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 16:58:18
 */
class DAppApplication : BaseApplication() {
    private lateinit var mWebView: DWebView

    companion object {
        fun isDebug() = DEBUG
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable ->
            //异常处理
            throwable.printStackTrace()
        }
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(p0: Boolean) {
                DWebView.setWebContentsDebuggingEnabled(DEBUG)
                mWebView.apply {
                    settings.apply {
                        //设置 缓存模式
                        cacheMode = if (NetworkUtils.isNetworkConnected()) {
                            WebSettings.LOAD_DEFAULT
                        } else {
                            WebSettings.LOAD_CACHE_ELSE_NETWORK
                        }
                        //设置渲染等级为高
                        setRenderPriority(WebSettings.RenderPriority.HIGH)
                        //开启 DOM storage API 功能
                        domStorageEnabled = true
                        //开启 database storage API 功能
                        databaseEnabled = true
                        val cacheDirPath = filesDir.absolutePath + packageName
                        //设置数据库缓存路径
                        databasePath = cacheDirPath
                        //设置  Application Caches 缓存目录
                        setAppCachePath(cacheDirPath)
                        //开启 Application Caches 功能
                        setAppCacheEnabled(true)
                    }
                    addJavascriptObject(TronPayApi(), TronPayApi.JS_Bridge_Tag)
                    webViewClient = object : WebViewClient() {
                        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                            // super.onReceivedSslError(view, handler, error);
                            // 主要运用这个函数 onReceivedSslError 这个函数，这个是因为不信任安全证书不能打开网页的回调，
                            // 在其中只要调用 handler.proceed（）；函数就可以，
                            // 值得注意的事要把super去掉，因为通过源码可以看到  super中有取消加载网页的方法。
                            //SSL_DATE_INVALID   证书的日期是无效的
                            //SSL_EXPIRED     证书已经过期
                            //SSL_INVALID    一个通用的错误发生
                            //SSL_UNTRUSTED  不受信任的证书颁发机构
                            handler.proceed()
                        }

                        override fun onPageFinished(p0: WebView?, p1: String?) {
                            super.onPageFinished(p0, p1)
                            evaluateJavascript(JsResUtils.getTronPayJs())
                        }

                        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                            if (url.startsWith("exit")) {
                                AppManager.instance.finishActivity(WebActivity::class.java)
                                return true
                            }
                            return false
                        }
                    }
                }
            }
        })
        mWebView = DWebView(this@DAppApplication)
    }

    fun getWebView(): DWebView {
        if (mWebView.parent != null) {
            (mWebView.parent as ViewGroup).removeView(mWebView)
        }
        return mWebView
    }
}
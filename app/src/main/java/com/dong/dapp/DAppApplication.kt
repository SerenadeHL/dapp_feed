package com.dong.dapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.view.ViewGroup
import com.dong.dapp.jsapi.TronPayApi
import com.dong.dapp.ui.mvp.web.DAppWebActivity
import com.dong.dapp.utils.AssetsUtils
import com.dong.dapp.utils.NetworkUtils
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.PushAgent
import io.reactivex.plugins.RxJavaPlugins
import me.serenadehl.base.BaseApplication
import me.serenadehl.base.utils.app.AppManager
import wendu.dsbridge.DWebView
import skin.support.SkinCompatManager
import com.umeng.message.IUmengRegisterCallback
import me.serenadehl.base.extensions.log
import com.alibaba.android.arouter.launcher.ARouter
import com.dong.dapp.utils.SystemUtils
import com.squareup.leakcanary.LeakCanary


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
        if (!SystemUtils.isMainProcess(this)) return
        initX5WebView()
        initLeakCanary()
        initRxJava()
        initARouter()
        initSkin()
        initUmeng()
    }

    /**
     * 初始化ARouter
     */
    private fun initARouter() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()   // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    /**
     * 初始友盟相关
     */
    private fun initUmeng() {
        //初始化SDK
        //TODO 替换密钥
        UMConfigure.init(this, "appkey", "channel", UMConfigure.DEVICE_TYPE_PHONE, "pushSecret")
        //友盟统计
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
        //友盟推送
        PushAgent.getInstance(this).register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                "注册成功：deviceToken：-------->  $deviceToken".log()
            }

            override fun onFailure(s: String, s1: String) {
                "注册失败：-------->  s:$s,s1:$s1".log()
            }
        })

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacksImpl() {
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                PushAgent.getInstance(activity).onAppStart()
            }

            override fun onActivityResumed(activity: Activity?) {
                MobclickAgent.onResume(activity)
            }

            override fun onActivityPaused(activity: Activity?) {
                MobclickAgent.onPause(activity)
            }
        })
    }

    /**
     * 初始化LeakCanary
     */
    private fun initLeakCanary() {
        LeakCanary.install(this)
    }

    /**
     * 初始化RxJava异常处理
     */
    private fun initRxJava() {
        RxJavaPlugins.setErrorHandler { throwable ->
            //异常处理
            throwable.printStackTrace()
        }
    }

    /**
     * 初始化腾讯X5内核WebView
     */
    private fun initX5WebView() {
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
                            evaluateJavascript(AssetsUtils.getAssets("TronWeb.js"))
                        }

                        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                            if (url.startsWith("exit")) {
                                AppManager.finishActivity(DAppWebActivity::class.java)
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

    /**
     * 初始化换肤组件
     */
    private fun initSkin() {
        SkinCompatManager.withoutActivity(this).loadSkin()
    }

    fun getWebView(): DWebView {
        if (mWebView.parent != null) {
            (mWebView.parent as ViewGroup).removeView(mWebView)
        }
        return mWebView
    }
}
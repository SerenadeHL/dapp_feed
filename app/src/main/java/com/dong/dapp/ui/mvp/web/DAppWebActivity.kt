package com.dong.dapp.ui.mvp.web

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dong.dapp.DAppApplication
import com.dong.dapp.R
import com.dong.dapp.constant.Router
import com.dong.dapp.constant.RouterParams
import com.dong.dapp.bean.statistics.ResultEnterDAppBean
import com.dong.dapp.bean.wallet.UserInfoBean
import com.dong.dapp.extensions.save
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.RequestManager
import com.dong.dapp.utils.PopupWindowUtils
import com.dong.dapp.utils.WebViewUtils
import com.dong.dapp.widget.SuspensionBall
import kotlinx.android.synthetic.main.activity_dapp_web.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.gone
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.extensions.visible
import wendu.dsbridge.DWebView


/**
 * DAppWeb容器页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:37:01
 */
@Route(path = Router.DAPP_WEB_ACTIVITY)
class DAppWebActivity : MVPBaseActivity<IDAppWebPresenter>(), IDAppWebView {
    @JvmField
    @Autowired(name = RouterParams.ID)
    var mPid: String? = null//DApp的id
    @JvmField
    @Autowired(name = RouterParams.URL)
    var mUrl: String? = null//DApp链接

    private lateinit var mWebView: DWebView
    private lateinit var mId: String//用户行为id
    private val mActions by lazy { mutableListOf<Map<String, String>>() }//用户行为

    override fun layout() = R.layout.activity_dapp_web

    override fun createPresenter() = DAppWebPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        mWebView = (application as DAppApplication).getWebView()
        mRootView.addView(
            mWebView, 0, ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        )
        mWebView.loadUrl(mUrl)

        WebViewUtils.setDrawDuringWindowsAnimating(mWebView)

        sb_ball.setListener(object : SuspensionBall.Listener {
            override fun onClick() {
                sb_ball.gone()
                //TODO 测试
                PopupWindowUtils.bottomPopupWindow(
                    this@DAppWebActivity, "我的账户", "", { sb_ball.visible() },
                    PopupWindowUtils.ButtonConfig("充值赢更多", PopupWindowUtils.ButtonConfig.BLUE_SOLID, true) {
                        toast("充值赢更多")
                    },
                    PopupWindowUtils.ButtonConfig("退出游戏", PopupWindowUtils.ButtonConfig.BLUE_NO_SOLID, true) {
                        toast("退出游戏")
                    }
                )
            }
        })

        RequestManager.getTronUserInfo()
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

        mPresenter.enterDApp(mPid ?: "")
    }

    override fun onDestroy() {
        if (::mId.isInitialized) {
            mActions.add(mapOf(System.currentTimeMillis().toString() to "-1"))
            mPresenter.exitDApp(mId, mActions)
        }
        mWebView.stopLoading()
        mWebView.clearView()
        mWebView.destroyDrawingCache()
        mWebView.loadUrl("about:blank")
        mWebView.clearHistory()
        mRootView.removeView(mWebView)
        super.onDestroy()
    }

    override fun enterDAppSuccess(data: ResultEnterDAppBean?) {
        mId = data?.id ?: ""
        "enterDAppSuccess------> 用户行为id=$mId".log()
    }

    override fun enterDAppFailed() {
        "enterDAppFailed------->".log()
    }
}
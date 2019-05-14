package com.axonomy.dapp_feed.ui.mvp.totalcount.revenuerules

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.method.ScrollingMovementMethod
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.coin.ResultRevenueRulesBean
import com.axonomy.dapp_feed.constant.Router
import kotlinx.android.synthetic.main.activity_revenue_rules.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log

/**
 * 游戏收益规则说明页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-14 12:00:24
 */
@Route(path = Router.REVENUE_RULES_ACTIVITY)
class RevenueRulesActivity : MVPBaseActivity<IRevenueRulesPresenter>(), IRevenueRulesView {

    private val mC2 by lazy { ContextCompat.getColor(this@RevenueRulesActivity, R.color.C2) }

    override fun layout() = R.layout.activity_revenue_rules

    override fun createPresenter() = RevenueRulesPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.revenue_rules)
        tv_html.movementMethod = ScrollingMovementMethod.getInstance()

        mPresenter.getRevenueRules()
    }

    override fun getRevenueRulesSuccess(data: ResultRevenueRulesBean?) {
        "getRevenueRulesSuccess-------> $data".log()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_html.text = Html.fromHtml(data?.html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            tv_html.text = Html.fromHtml(data?.html)
        }
    }

    override fun getRevenueRulesFailed() {
        "getRevenueRulesFailed------->".log()
    }
}

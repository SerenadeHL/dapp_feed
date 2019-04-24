package com.dong.dapp.ui.mvp.totalcashcount

import com.dong.dapp.R
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.title_layout.*

import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.visible

/**
 * 现金资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
class TotalCashCountActivity : MVPBaseActivity<ITotalCashCountPresenter>(), ITotalCashCountView {
    private val mC2 by lazy { ContextCompat.getColor(this@TotalCashCountActivity, R.color.C2) }

    override fun layout() = R.layout.activity_total_cash_count

    override fun createPresenter() = TotalCashCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        hideStatusBar()
        setStatusBarTranslucent(false)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.cash_assets)
    }

}

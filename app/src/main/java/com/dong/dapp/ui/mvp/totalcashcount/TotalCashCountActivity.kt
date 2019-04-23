package com.dong.dapp.ui.mvp.totalcashcount

import com.dong.dapp.R
import android.os.Bundle

import me.serenadehl.base.base.mvpbase.MVPBaseActivity

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
class TotalCashCountActivity : MVPBaseActivity<ITotalCashCountPresenter>(), ITotalCashCountView {

    override fun layout() = R.layout.activity_total_cash_count

    override fun createPresenter() = TotalCashCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupStatusBar()
    }

}

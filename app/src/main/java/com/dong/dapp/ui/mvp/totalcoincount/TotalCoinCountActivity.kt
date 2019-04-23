package com.dong.dapp.ui.mvp.totalcoincount

import com.dong.dapp.R
import android.os.Bundle

import me.serenadehl.base.base.mvpbase.MVPBaseActivity

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountActivity : MVPBaseActivity<ITotalCoinCountPresenter>(), ITotalCoinCountView {

    override fun layout() = R.layout.activity_total_coin_count

    override fun createPresenter() = TotalCoinCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

    }

}

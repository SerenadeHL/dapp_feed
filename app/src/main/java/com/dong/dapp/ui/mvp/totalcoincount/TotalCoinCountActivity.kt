package com.dong.dapp.ui.mvp.totalcoincount

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import com.dong.dapp.adapter.recyclerview.CoinAdapter
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.app_recycle_header_total_coin.view.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.dp
import me.serenadehl.base.extensions.getStatusBarHeight

/**
 * 金币资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountActivity : MVPBaseActivity<ITotalCoinCountPresenter>(), ITotalCoinCountView {
    private val mC2 by lazy { ContextCompat.getColor(this@TotalCoinCountActivity, R.color.C2) }

    private val mHeader by lazy {
        layoutInflater.inflate(R.layout.app_recycle_header_total_coin, null, false).apply {
            setPadding(0, getStatusBarHeight() + dimen(R.dimen.L2), 0, 0)
        }
    }

    private val mAdapter by lazy { CoinAdapter().apply { addHeaderView(mHeader) } }

    override fun layout() = R.layout.activity_total_coin_count

    override fun createPresenter() = TotalCoinCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setStatusBarTranslucent(false)
        //返回按钮
        iv_back.setImageResource(R.mipmap.white_left_arrow)
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.coin_assets)
        tv_title.setTextColor(mC2)

        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()


        rv_coin.apply {
            adapter = mAdapter
        }

        //TODO 测试
        mHeader.tv_money.text = "¥4223.44"
        mHeader.tv_cumulative_gain.text = "¥4134.55"
        mHeader.tv_today_obtain.text = "¥41.00"

    }

}

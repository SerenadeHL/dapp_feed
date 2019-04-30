package com.dong.dapp.ui.mvp.totalcoincount

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.dong.dapp.R
import com.dong.dapp.adapter.recyclerview.CoinAdapter
import com.dong.dapp.bean.coin.ResultTotalCoinBean
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.app_recycle_header_total_coin.view.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.invisible

/**
 * 金币资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountActivity : MVPBaseActivity<ITotalCoinCountPresenter>(), ITotalCoinCountView {
    private val mC2 by lazy { ContextCompat.getColor(this@TotalCoinCountActivity, R.color.C2) }
    private val mC6 by lazy { ContextCompat.getColor(this@TotalCoinCountActivity, R.color.C6) }

    private val mHeader by lazy {
        layoutInflater.inflate(R.layout.app_recycle_header_total_coin, null, false).apply {
            (tv_money.layoutParams as ConstraintLayout.LayoutParams).topMargin += getStatusBarHeight() + dimen(R.dimen.L2)
        }
    }

    private var mYOffset = 0

    private var mAlphaDistance = 0

    private val mAdapter by lazy { CoinAdapter(this@TotalCoinCountActivity).apply { addHeaderView(mHeader) } }

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
        v_header_divider.invisible()

        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()

        rv_coin.apply {
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (mAlphaDistance <= 0) {
                        mAlphaDistance = mHeader.measuredHeight - getStatusBarHeight() - dimen(R.dimen.L2) * 2
                    }

                    //记录滑动距离
                    mYOffset += dy

                    //计算透明度
                    val alpha = 1F - if (mYOffset < mAlphaDistance) {
                        (mAlphaDistance - mYOffset).toFloat() / mAlphaDistance.toFloat()
                    } else {
                        0F
                    }
                    //设置透明度
                    v_top_bg.alpha = alpha

                    if (alpha > 0.5F) {
                        setStatusBarTranslucent(true)
                        iv_back.setImageResource(R.mipmap.black_left_arrow)
                        tv_title.setTextColor(mC6)
                    } else {
                        setStatusBarTranslucent(false)
                        iv_back.setImageResource(R.mipmap.white_left_arrow)
                        tv_title.setTextColor(mC2)
                    }
                }
            })
        }

        //TODO 测试
        mHeader.tv_money.text = "¥4223.44"
        mHeader.tv_cumulative_gain.text = "¥4134.55"
        mHeader.tv_today_obtain.text = "¥41.00"
        val data = arrayListOf(
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "进行中", "+ ¥23.44"),
            ResultTotalCoinBean("划转到JetAxon", "2019.4.6 14:22", "失败", "- ¥12"),
            ResultTotalCoinBean("邀请好友{用户名}充值返现", "2019.4.6 14:22", "已完成", "+ ¥2.66"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥1"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥2"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥3"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥4"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥5"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥6"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥7"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥8"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥9"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥10"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥11"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥12"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥13"),
            ResultTotalCoinBean("邀请好友激活奖励", "2019.4.6 14:22", "已完成", "+ ¥14")
        )
        mAdapter.setNewData(data)
    }

}

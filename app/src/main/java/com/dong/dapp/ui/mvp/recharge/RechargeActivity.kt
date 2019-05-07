package com.dong.dapp.ui.mvp.recharge

import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_recharge.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_recharge.v_status_bar
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.title_layout.*

import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.visible

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
@Route(path = "/ui/mvp/recharge/RechargeActivity")
class RechargeActivity : MVPBaseActivity<IRechargePresenter>(), IRechargeView {
    private val mC2 by lazy { ContextCompat.getColor(this@RechargeActivity, R.color.C2) }

    override fun layout() = R.layout.activity_recharge

    override fun createPresenter() = RechargePresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setStatusBarTranslucent(false)
        //返回按钮
        iv_back.setImageResource(R.mipmap.white_left_arrow)
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setTextColor(mC2)
        tv_title.setText(R.string.recharge_game_coins)
        v_header_divider.invisible()
        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()

    }

}

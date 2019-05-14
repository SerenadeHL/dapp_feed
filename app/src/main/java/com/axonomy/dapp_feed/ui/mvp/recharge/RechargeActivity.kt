package com.axonomy.dapp_feed.ui.mvp.recharge

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOptionsBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOptionsItemBean
import com.axonomy.dapp_feed.bean.recharge.ResultRechargeOrderBean
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.utils.PayUtils
import kotlinx.android.synthetic.main.activity_recharge.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.app.SystemUtils

/**
 * 充值页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
@Route(path = Router.RECHARGE_ACTIVITY)
class RechargeActivity : MVPBaseActivity<IRechargePresenter>(), IRechargeView {
    private var mCurrentSelectOption: TextView? = null
    private val mColumnCount = 3

    private val mC2 by lazy { ContextCompat.getColor(this@RechargeActivity, R.color.C2) }
    private val mC6 by lazy { ContextCompat.getColor(this@RechargeActivity, R.color.C6) }
    private val mL5 by lazy { dimen(R.dimen.L5) }

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

        btn_pay.setOnClickListener {
            mPresenter.getRechargeOrder((mCurrentSelectOption?.tag as ResultRechargeOptionsItemBean?)?.id ?: "")
        }

        loadData()
    }

    private fun loadData() {
        mPresenter.getRechargeOptions()
    }

    override fun getRechargeOptionsSuccess(data: ResultRechargeOptionsBean?) {
        "getRechargeOptionsSuccess-------> $data".log()
        var subLinear: LinearLayout? = null
        data?.items?.withIndex()?.forEach {
            val isFirst = it.index % mColumnCount == 0
            if (isFirst) {
                subLinear = generateSubLinear()
                ll_options.addView(subLinear)
            }
            subLinear?.addView(generateOption(it.value, !isFirst))
        }

        try {
            //选中第一个选项
            (ll_options.getChildAt(0) as LinearLayout).getChildAt(0).performClick()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getRechargeOptionsFailed() {
        "getRechargeOptionsFailed------->".log()
    }

    override fun getRechargeOrderSuccess(data: ResultRechargeOrderBean?) {
        "getRechargeOrderSuccess-------> $data".log()
        PayUtils.alipay(this@RechargeActivity, data?.info ?: "") {
            "resultStatus-------> ${it["resultStatus"]}".log()
            if (it["resultStatus"] == "9000") {
                ARouter.getInstance()
                    .build(Router.RECHARGE_SUCCESS_ACTIVITY)
                    .navigation()
            }
        }
    }

    override fun getRechargeOrderFailed() {
        "getRechargeOrderFailed------->".log()
    }

    /**
     * 生成行
     */
    private fun generateSubLinear(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dimen(R.dimen.dp_recharge_sublinear_height)
            ).apply { weightSum = mColumnCount.toFloat() }
        }
    }

    /**
     * 生成选项
     */
    private fun generateOption(item: ResultRechargeOptionsItemBean, margin: Boolean): TextView {
        return TextView(this).apply {
            val width =
                (SystemUtils.getScreenWidth(this@RechargeActivity) - dimen(R.dimen.L1) * 2 - dimen(R.dimen.dp_recharge_option_margin_start) * (mColumnCount - 1)) / mColumnCount
            layoutParams = LinearLayout.LayoutParams(width, mL5).apply {
                if (margin) marginStart = dimen(R.dimen.dp_recharge_option_margin_start)
            }
            text = item.title
            textSize = 16F
            setTextColor(mC6)
            gravity = Gravity.CENTER
            includeFontPadding = false
            setBackgroundResource(R.drawable.round_rect_no_solid_quota_bg)
            setOnClickListener {
                if (mCurrentSelectOption == this) return@setOnClickListener//点击相同按钮不做处理
                unSelect(mCurrentSelectOption)
                select(this)
                mCurrentSelectOption = this
                tv_cost.text = String.format(getString(R.string.money_with_symbol), item.cost)
            }
            tag = item
        }
    }

    /**
     * 选择
     */
    private fun select(option: TextView?) {
        option?.apply {
            setTextColor(mC2)
            setBackgroundResource(R.drawable.g2_vertical_round_rect)
        }
    }

    /**
     * 取消选择
     */
    private fun unSelect(option: TextView?) {
        option?.apply {
            setTextColor(mC6)
            setBackgroundResource(R.drawable.round_rect_no_solid_quota_bg)
        }
    }

}

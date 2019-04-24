package com.dong.dapp.ui.mvp.transfer

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.gone
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.extensions.visible

/**
 * 划转页父类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 11:10:26
 */
abstract class TransferActivity : MVPBaseActivity<ITransferPresenter>() {
    private var mSelected = 0
    private val mOptions by lazy { arrayOf(tv_option0, tv_option1, tv_option2, tv_option3, tv_option4, tv_option5) }

    private val mC2 by lazy { ContextCompat.getColor(this@TransferActivity, R.color.C2) }
    private val mC6 by lazy { ContextCompat.getColor(this@TransferActivity, R.color.C6) }

    override fun createPresenter() = TransferPresenter()

    override fun layout() = R.layout.activity_transfer

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.visible()
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(getTitleResId())
        //余额标签
        tv_balance_label.setText(getBalanceLabelResId())
        if (showQuestionMark()) iv_question_mark.visible() else iv_question_mark.invisible()
        tv_balance.text = "¥23.44"

        tv_quota.setText(getQuotaResId())

        tv_quota.textSize = getQuotaTextSPSize()

        //TODO 测试
        tv_option0.text = "20元"
        tv_option1.text = "50元"
        tv_option2.text = "100元"
        tv_option3.text = "200元"
        tv_option4.text = "500元"
        tv_option5.text = "1000元"

        tv_explanation.text =
            "划转说明\n将金币实时划转到JetAxon.com，再前往该网站提取奖励。\n玩游戏和邀请好友，可赢取更多金币和现金奖励。\n被划转的金币，只会显示在JetAxon.com，暂时无法用于游戏。"

        btn_confirm_extraction.setOnClickListener {
            //TODO 确认提取
            toast("确认提取")
        }

        //初始化选项
        for ((index, option) in mOptions.withIndex()) {
            option.setOnClickListener {
                if (mSelected == index) return@setOnClickListener
                unSelectOption(mSelected)//取消已经选择的
                selectOption(index)//选择新点击的
                mSelected = index
            }
        }

        //选中默认的选项
        mOptions[mSelected].performClick()
    }

    /**
     * 选中选项
     */
    private fun selectOption(index: Int) {
        mOptions[index].apply {
            setBackgroundResource(R.drawable.round_rect_solid_quota_bg)
            setTextColor(mC2)
        }
    }

    /**
     * 取消选中选项
     */
    private fun unSelectOption(index: Int) {
        mOptions[index].apply {
            setBackgroundResource(R.drawable.round_rect_no_solid_quota_bg)
            setTextColor(mC6)
        }
    }

    /**
     * 获取标题资源id
     */
    abstract fun getTitleResId(): Int

    /**
     * 获取余额标签资源id
     */
    abstract fun getBalanceLabelResId(): Int

    /**
     * 是否显示问号
     */
    abstract fun showQuestionMark(): Boolean

    /**
     * 获取额度资源id
     */
    abstract fun getQuotaResId(): Int

    /**
     * 获取额度字体大小
     */
    abstract fun getQuotaTextSPSize(): Float
}

package com.dong.dapp.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_success.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity

/**
 * 成功页父类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-08 19:51:40
 */
abstract class SuccessActivity : BaseActivity() {
    private val mC2 by lazy { ContextCompat.getColor(this@SuccessActivity, R.color.C2) }

    override fun layout() = R.layout.activity_success

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(getTitleResId())

        tv_description.text = getDescription()

        btn_ok.setOnClickListener { finish() }
    }

    abstract fun getTitleResId(): Int

    abstract fun getDescription(): SpannableString
}
package com.dong.dapp.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.view.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.extensions.visible

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-23 14:58:51
 */
class SettingsActivity : BaseActivity() {

    private val mC2 by lazy { ContextCompat.getColor(this@SettingsActivity, R.color.C2) }

    override fun layout() = R.layout.activity_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.visible()
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.settings)

        tv_certification.setOnClickListener {
            //TODO 实名认证
            toast("实名认证")
        }

        tv_version_update.setOnClickListener {
            //TODO 版本更新
            toast("版本更新")
        }

        tv_logout.setOnClickListener {
            //TODO 退出登录
            toast("退出登录")
        }
    }

}
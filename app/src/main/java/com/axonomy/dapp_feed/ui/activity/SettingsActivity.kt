package com.axonomy.dapp_feed.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.utils.DialogUtils
import com.axonomy.dapp_feed.utils.LoginUtils
import com.axonomy.dapp_feed.utils.PopupWindowUtils
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.toast

/**
 * 设置页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-23 14:58:51
 */
@Route(path = Router.SETTINGS_ACTIVITY)
class SettingsActivity : BaseActivity() {

    private val mC2 by lazy { ContextCompat.getColor(this@SettingsActivity, R.color.C2) }

    override fun layout() = R.layout.activity_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.settings)

        tv_certification.setOnClickListener {
            ARouter.getInstance()
                .build(Router.KYC_ACTIVITY)
                .navigation()
        }

        tv_version_update.setOnClickListener {
            //TODO 版本更新
            toast("版本更新")
        }

        tv_logout.setOnClickListener {
            DialogUtils.show(this@SettingsActivity, R.string.logout,
                DialogInterface.OnClickListener { _, _ ->
                    LoginUtils.removeLoginTag()
                    finish()
                })
        }
    }

}
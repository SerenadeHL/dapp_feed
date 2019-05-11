package com.axonomy.dapp_feed.ui.mvp.settings

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.update.ResultUpdateInfoBean
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.utils.DialogUtils
import com.axonomy.dapp_feed.utils.LoginUtils
import com.axonomy.dapp_feed.widget.UpdateDialog
import com.dong.dapp.utils.SystemUtils
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast

/**
 * 设置页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-23 14:58:51
 */
@Route(path = Router.SETTINGS_ACTIVITY)
class SettingsActivity : MVPBaseActivity<ISettingsPresenter>(), ISettingsView {
    private lateinit var mUpdateDialog: UpdateDialog//更新弹窗

    private val mC2 by lazy { ContextCompat.getColor(this@SettingsActivity, R.color.C2) }

    override fun createPresenter() = SettingsPresenter()

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
                .build(Router.PERSONAL_INFO_ACTIVITY)
                .navigation()
        }

        tv_version_update.setOnClickListener {
            tv_version_update.isEnabled = false
            toast(R.string.checking_update)
            mPresenter.getUpdateInfo()
        }

        tv_logout.setOnClickListener {
            DialogUtils.show(this@SettingsActivity, R.string.logout,
                DialogInterface.OnClickListener { _, _ ->
                    LoginUtils.removeLoginTag()
                    finish()
                })
        }
    }

    override fun getUpdateInfoSuccess(data: ResultUpdateInfoBean?) {
        tv_version_update.isEnabled = true
        "getUpdateInfoSuccess-------> $data".log()
        if (data?.updateType == 2 || SystemUtils.getAppVersionCode().toInt() == data?.versionCode || data == null) {//不显示
            toast(R.string.newest_version)
            return
        }
        mUpdateDialog = UpdateDialog(this, data)
        mUpdateDialog.show()
    }

    override fun getUpdateInfoFailed() {
        tv_version_update.isEnabled = true
        toast(R.string.checking_update_failed)
        "getUpdateInfoFailed------->".log()
    }
}
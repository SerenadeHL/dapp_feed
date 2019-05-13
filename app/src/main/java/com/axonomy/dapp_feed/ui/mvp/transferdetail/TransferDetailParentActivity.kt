package com.axonomy.dapp_feed.ui.mvp.transferdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.MVPBaseActivity

/**
 * 划转明细页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 16:20:51
 */
abstract class TransferDetailParentActivity<P : IBasePresenter> : MVPBaseActivity<P>() {

    private val mC2 by lazy { ContextCompat.getColor(this@TransferDetailParentActivity, R.color.C2) }

    override fun layout() = R.layout.activity_transfer_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.transfer_detail)
    }
}
package com.dong.dapp.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import com.dong.dapp.bean.coin.TotalCoinBean
import kotlinx.android.synthetic.main.activity_transfer_detail.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.visible

/**
 * 划转明细页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 16:20:51
 */
class TransferDetailActivity : BaseActivity() {
    private val mC2 by lazy { ContextCompat.getColor(this@TransferDetailActivity, R.color.C2) }

    override fun layout() = R.layout.activity_transfer_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.visible()
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.transfer_detail)

        tv_money.text = "¥23.44"
        tv_detail.text = "划转到JetAxon"
        tv_category.text = "划转"
        tv_status.text = "已完成"
        tv_time.text = "2019.1.3 16:44:33"
        tv_serial_number.text = "728754928351"
        intent.getParcelableExtra<TotalCoinBean>("data").log()
    }

}
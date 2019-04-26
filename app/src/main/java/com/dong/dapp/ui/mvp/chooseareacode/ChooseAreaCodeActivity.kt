package com.dong.dapp.ui.mvp.chooseareacode

import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_choose_area_code.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.bean.areacode.ResultAreaCodeBean
import kotlinx.android.synthetic.main.title_layout.*

import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
class ChooseAreaCodeActivity : MVPBaseActivity<IChooseAreaCodePresenter>(), IChooseAreaCodeView {
    private val mC2 by lazy { ContextCompat.getColor(this@ChooseAreaCodeActivity, R.color.C2) }

    override fun layout() = R.layout.activity_choose_area_code

    override fun createPresenter() = ChooseAreaCodePresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.choose_area_code)
        cl_title.setBackgroundColor(mC2)

        mPresenter.getAreaCode()
    }

    override fun setAreaCode(data: List<ResultAreaCodeBean>) {
        //TODO 设置数据
        "areaCode---------> $data".log()
    }

}

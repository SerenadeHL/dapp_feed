package com.dong.dapp.ui.mvp.chooseareacode

import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_choose_area_code.*
import android.os.Bundle

import me.serenadehl.base.base.mvpbase.MVPBaseActivity

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
class ChooseAreaCodeActivity : MVPBaseActivity<IChooseAreaCodePresenter>(), IChooseAreaCodeView {

    override fun layout() = R.layout.activity_choose_area_code

    override fun createPresenter() = ChooseAreaCodePresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

    }

}

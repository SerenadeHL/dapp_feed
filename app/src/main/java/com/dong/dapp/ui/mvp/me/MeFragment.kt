package com.dong.dapp.ui.mvp.me

import com.dong.dapp.R
import android.os.Bundle

import me.serenadehl.base.base.mvpbase.MVPBaseFragment

/**
 * 我页面Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
class MeFragment : MVPBaseFragment<IMePresenter>(), IMeView {

    override fun layout() = R.layout.fragment_me

    override fun createPresenter() = MePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}

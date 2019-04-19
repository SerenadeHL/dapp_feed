package com.dong.dapp.adapter.viewpager

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dong.dapp.ui.mvp.gamesquare.GameSquareFragment
import com.dong.dapp.ui.mvp.me.MeFragment

/**
 * DApp列表首页的ViewPagerAdapter
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 20:08:46
 */
class ApplicationListAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val mTitles = arrayListOf(
        "测试1", "测试2",
        "测试3", "测试4",
        "测试5", "测试6",
        "测试7", "测试8",
        "测试9", "测试10"
    )

    //TODO 暂时用我页面测试
    private val mFragments = arrayListOf(
        GameSquareFragment(), MeFragment(),
        MeFragment(), MeFragment(),
        MeFragment(), MeFragment(),
        MeFragment(), MeFragment(),
        MeFragment(), MeFragment()
    )

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size

    override fun getPageTitle(position: Int) = mTitles[position]

}
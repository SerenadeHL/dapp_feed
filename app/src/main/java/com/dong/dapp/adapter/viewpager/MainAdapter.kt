package com.dong.dapp.adapter.viewpager

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dong.dapp.ui.mvp.gamesquare.GameSquareFragment
import com.dong.dapp.ui.mvp.me.MeFragment

/**
 * 首页"应用"和"我"的ViewPagerAdapter
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 20:08:46
 */
class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragments = arrayListOf(GameSquareFragment(), MeFragment())

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size

}
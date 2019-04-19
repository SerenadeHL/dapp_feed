package com.dong.dapp.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.dong.dapp.R
import com.dong.dapp.adapter.viewpager.MainAdapter
import com.dong.dapp.utils.LoginUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity

/**
 * 主页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-16 19:46:44
 */
class MainActivity : BaseActivity() {
    companion object {
        const val GAME = 0
        const val ME = 1
    }

    override fun layout() = R.layout.activity_main

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupStatusBar()

        setStatusBarBackgroundResource(R.drawable.g1_horizontal, false)
        cl_title.setBackgroundResource(R.drawable.g1_horizontal)

        vp_viewpager.apply {
            offscreenPageLimit = 3
            adapter = MainAdapter(supportFragmentManager)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {

                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                }

                override fun onPageSelected(page: Int) {
                    switchTab(page, false)
                }
            })
        }

        cl_game.setOnClickListener { switchTab(GAME, true) }
        cl_me.setOnClickListener { switchTab(ME, true) }

        //TODO 提前到闪屏页获取权限
        RxPermissions(this@MainActivity)
            .request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .subscribe()

        cl_game.performClick()

        //TODO 模拟登录
        LoginUtils.saveLoginTag("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2NDU4NywiaWF0IjoxNTU1NTU4MDMyLCJleHAiOjE1NjMzMzQwMzJ9.oeWtWfEazaRCqL0FSF8uKW_Ov1_qe6cXIh_uJW7ihwo")
    }

    /**
     * 切换标签页
     * @param index 底部菜单栏坐标，从0开始由左到右计算
     * @param click 是否为点击切换，true为点击，false为ViewPager滑动关联
     */
    fun switchTab(index: Int, click: Boolean) {
        when (index) {
            GAME -> {
                tv_title.setText(R.string.title_game_square)
                tv_game_icon.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.C6))
                tv_me_icon.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_B9BCCE))
                iv_game_icon.setImageResource(R.mipmap.game_active)
                iv_me_icon.setImageResource(R.mipmap.me_unactive)
            }
            ME -> {
                tv_title.setText(R.string.title_me)
                tv_game_icon.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_B9BCCE))
                tv_me_icon.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.C6))
                iv_game_icon.setImageResource(R.mipmap.game_unactive)
                iv_me_icon.setImageResource(R.mipmap.me_active)
            }
        }

        if (!click) return
        vp_viewpager.currentItem = index
    }
}

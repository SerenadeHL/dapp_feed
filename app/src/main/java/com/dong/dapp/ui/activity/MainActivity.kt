package com.dong.dapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import com.dong.dapp.ui.mvp.gamesquare.GameSquareFragment
import com.dong.dapp.ui.mvp.login.LoginActivity
import com.dong.dapp.ui.mvp.me.MeFragment
import com.dong.dapp.utils.LoginUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.startActivity
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.extensions.visible


/**
 * 主页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-16 19:46:44
 */
class MainActivity : BaseActivity() {
    companion object {
        const val GAME = 0//游戏广场
        const val ME = 1//我

        const val EXIT_INTERVAL = 2000//双击返回键退出的间隔
    }

    private var mLastBackPressed = 0L//上次按退出键的时间

    private val mC2 by lazy { ContextCompat.getColor(this@MainActivity, R.color.C2) }
    private val mC6 by lazy { ContextCompat.getColor(this@MainActivity, R.color.C6) }
    private val mB9BCCE by lazy { ContextCompat.getColor(this@MainActivity, R.color.color_B9BCCE) }

    private val mGameSquareFragment by lazy { GameSquareFragment() }//游戏广场
    private val mMeFragment by lazy { MeFragment() }//我

    override fun layout() = R.layout.activity_main

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupStatusBar()
        iv_back.invisible()

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment, mMeFragment)
            .hide(mMeFragment)
            .add(R.id.fl_fragment, mGameSquareFragment)
            .commitNow()

        cl_game.setOnClickListener { switchTab(GAME) }
        cl_me.setOnClickListener {
            if (!LoginUtils.isLogin()) {
                startActivity<LoginActivity>()
                return@setOnClickListener
            }
            switchTab(ME)
        }

        cl_game.performClick()
    }

    /**
     * 双击返回键退出处理
     */
    override fun onBackPressed() {
        val backPressed = System.currentTimeMillis()
        if (backPressed - mLastBackPressed > EXIT_INTERVAL) {
            toast(R.string.press_again_to_exit)
            mLastBackPressed = backPressed
        } else {
            super.onBackPressed()
        }
    }

    /**
     * 切换标签页
     * @param index 底部菜单栏坐标，从0开始由左到右计算
     */
    private fun switchTab(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when (index) {
            GAME -> {
                //改变状态栏和标题栏的颜色
                setStatusBarBackgroundResource(R.drawable.g1_horizontal, false)
                cl_title.setBackgroundResource(R.drawable.g1_horizontal)
                tv_title.setTextColor(mC2)
                v_header_divider.invisible()

                tv_title.setText(R.string.title_game_square)
                tv_game_icon.setTextColor(mC6)
                tv_me_icon.setTextColor(mB9BCCE)
                iv_game_icon.setImageResource(R.mipmap.game_active)
                iv_me_icon.setImageResource(R.mipmap.me_unactive)
                transaction.hide(mMeFragment).show(mGameSquareFragment)
            }
            ME -> {
                //改变状态栏和标题栏的颜色
                setStatusBarColor(mC2, true)
                cl_title.setBackgroundColor(mC2)
                tv_title.setTextColor(mC6)
                v_header_divider.visible()

                tv_title.setText(R.string.title_me)
                tv_game_icon.setTextColor(mB9BCCE)
                tv_me_icon.setTextColor(mC6)
                iv_game_icon.setImageResource(R.mipmap.game_unactive)
                iv_me_icon.setImageResource(R.mipmap.me_active)
                transaction.hide(mGameSquareFragment).show(mMeFragment)
            }
        }
        transaction.commitNow()
    }
}

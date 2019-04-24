package com.dong.dapp.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import com.dong.dapp.ui.mvp.gamesquare.GameSquareFragment
import com.dong.dapp.ui.mvp.me.MeFragment
import com.dong.dapp.ui.mvp.transfer.TransferActivity
import com.dong.dapp.ui.mvp.transfer.TransferCashActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.internal.util.BackpressureHelper.add
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.startActivity


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

    private val mC2 by lazy { ContextCompat.getColor(this@MainActivity, R.color.C2) }
    private val mC6 by lazy { ContextCompat.getColor(this@MainActivity, R.color.C6) }
    private val mC9 by lazy { ContextCompat.getColor(this@MainActivity, R.color.C9) }
    private val mB9BCCE by lazy { ContextCompat.getColor(this@MainActivity, R.color.color_B9BCCE) }

    private val mGameSquareFragment by lazy { GameSquareFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun layout() = R.layout.activity_main

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupStatusBar()

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment, mMeFragment)
            .hide(mMeFragment)
            .add(R.id.fl_fragment, mGameSquareFragment)
            .commitNow()

        cl_game.setOnClickListener { switchTab(GAME) }
        cl_me.setOnClickListener { switchTab(ME) }

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
//        LoginUtils.saveLoginTag("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2NDU4NywiaWF0IjoxNTU1NTU4MDMyLCJleHAiOjE1NjMzMzQwMzJ9.oeWtWfEazaRCqL0FSF8uKW_Ov1_qe6cXIh_uJW7ihwo")
        startActivity<TransferCashActivity>()
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
                v_divider.setBackgroundResource(R.drawable.g1_horizontal)

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
                v_divider.setBackgroundColor(mC9)

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

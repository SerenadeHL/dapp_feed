package com.dong.dapp.ui.mvp.gamesquare

import android.os.Bundle
import com.dong.dapp.R
import com.dong.dapp.ui.mvp.totalcashcount.TotalCashCountActivity
import com.dong.dapp.ui.mvp.totalcoincount.TotalCoinCountActivity
import kotlinx.android.synthetic.main.app_recycle_item_game_square.view.*
import kotlinx.android.synthetic.main.fragment_game_square.view.*
import me.serenadehl.base.base.mvpbase.MVPBaseFragment
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.startActivity
import me.serenadehl.base.extensions.toast

/**
 * 游戏广场页Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquareFragment : MVPBaseFragment<IGameSquarePresenter>(), IGameSquareView {

    override fun layout() = R.layout.fragment_game_square

    override fun createPresenter() = GameSquarePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.tv_announcement.text = "妖精种族，重磅来袭！构筑卡牌 探索奇妙梦境世界！"
        mRootView.cv_coin.setOnClickListener { startActivity<TotalCoinCountActivity>() }
        mRootView.cv_coin.setOnClickListener { startActivity<TotalCashCountActivity>() }
        mRootView.tv_coin.text = "2.56"
        mRootView.tv_cash.text = "¥66.56"
        mRootView.tv_sign.setOnClickListener {
            //TODO 点我签到
            toast("点我签到")
        }
        mRootView.tv_invite.setOnClickListener {
            //TODO 邀请好友赚现金
            toast("邀请好友赚现金")
        }

        //隐藏第一个Item上方分割线
        mRootView.cl_dapp1.v_top_divider.invisible()

        mRootView.cl_dapp1.tv_name.text = "HyperSnakes"
        mRootView.cl_dapp1.iv_logo.setImageResource(R.mipmap.ic_launcher)
        mRootView.cl_dapp1.tv_description.text = "妖精种族，重磅来袭！"
        mRootView.cl_dapp1.tv_playing_count.text = "2192人正在玩"

        mRootView.cl_dapp2.tv_name.text = "Tronjoy"
        mRootView.cl_dapp2.iv_logo.setImageResource(R.mipmap.ic_launcher)
        mRootView.cl_dapp2.tv_description.text = "构筑卡牌 探索奇妙梦境世界"
        mRootView.cl_dapp2.tv_playing_count.text = "4556人正在玩"

        mRootView.cl_dapp3.tv_name.text = "Traps"
        mRootView.cl_dapp3.iv_logo.setImageResource(R.mipmap.ic_launcher)
        mRootView.cl_dapp3.tv_description.text = "独立RUIUIY竞技类游戏"
        mRootView.cl_dapp3.tv_playing_count.text = "354人正在玩"

    }

}

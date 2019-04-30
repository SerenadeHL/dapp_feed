package com.dong.dapp.ui.mvp.gamesquare

import android.os.Bundle
import com.dong.dapp.R
import com.dong.dapp.bean.multipage.ResultMultiPageBean
import com.dong.dapp.bean.gamesquare.ResultDAppItemBean
import com.dong.dapp.extensions.showRound
import com.dong.dapp.ui.mvp.totalcashcount.TotalCashCountActivity
import com.dong.dapp.ui.mvp.totalcoincount.TotalCoinCountActivity
import com.dong.dapp.ui.mvp.web.WebActivity
import kotlinx.android.synthetic.main.app_recycle_item_game_square.view.*
import kotlinx.android.synthetic.main.fragment_game_square.view.*
import me.serenadehl.base.base.mvpbase.MVPBaseFragment
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.startActivity
import me.serenadehl.base.extensions.toast

/**
 * 游戏广场页Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquareFragment : MVPBaseFragment<IGameSquarePresenter>(), IGameSquareView {
    private var mPage = 0
    private var mPageSize = 3

    override fun layout() = R.layout.fragment_game_square

    override fun createPresenter() = GameSquarePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.tv_announcement.text =
            "妖精种族，重磅来袭！构筑卡牌 探索奇妙梦境世界！妖精种族，重磅来袭！构筑卡牌 探索奇妙梦境世界！妖精种族，重磅来袭！构筑卡牌 探索奇妙梦境世界！妖精种族，重磅来袭！构筑卡牌 探索奇妙梦境世界！"
        mRootView.tv_announcement.requestFocus()
        mRootView.cv_coin.setOnClickListener { startActivity<TotalCoinCountActivity>() }
        mRootView.cv_cash.setOnClickListener { startActivity<TotalCashCountActivity>() }
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

        //隐藏Item上方分割线
        mRootView.cl_dapp1.v_top_divider.invisible()
        mRootView.cl_dapp2.v_top_divider.invisible()
        mRootView.cl_dapp3.v_top_divider.invisible()

        //TODO 测试
        mRootView.iv_first_charge.setOnClickListener { toast("限时首充特惠") }

        getData()
    }

    private fun getData() {
        mPresenter.getDAppList(mPage, mPageSize)
    }

    override fun getDAppListSuccess(data: ResultMultiPageBean<ResultDAppItemBean>?) {
        "getDAppListSuccess-------> $data".log()

        val items = data?.items ?: return

        if (items.isEmpty()) return

        try {
            val radiusDp = 15F
            items[0].apply {
                mRootView.cl_dapp1.tv_name.text = title
                mRootView.cl_dapp1.iv_logo.showRound(logo, radiusDp)
                mRootView.cl_dapp1.tv_description.text = intro
                mRootView.cl_dapp1.tv_playing_count.text = String.format(getString(R.string.play_count), count)
                mRootView.cl_dapp1.setOnClickListener { enterDApp(this) }
            }
            items[1].apply {
                mRootView.cl_dapp2.tv_name.text = title
                mRootView.cl_dapp2.iv_logo.showRound(logo, radiusDp)
                mRootView.cl_dapp2.tv_description.text = intro
                mRootView.cl_dapp2.tv_playing_count.text = String.format(getString(R.string.play_count), count)
                mRootView.cl_dapp2.setOnClickListener { enterDApp(this) }
            }
            items[2].apply {
                mRootView.cl_dapp3.tv_name.text = title
                mRootView.cl_dapp3.iv_logo.showRound(logo, radiusDp)
                mRootView.cl_dapp3.tv_description.text = intro
                mRootView.cl_dapp3.tv_playing_count.text = String.format(getString(R.string.play_count), count)
                mRootView.cl_dapp3.setOnClickListener { enterDApp(this) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enterDApp(item: ResultDAppItemBean) {
        WebActivity.start(requireActivity(), item.pid, item.url)
    }

    override fun getDAppListFailed() {
        "getDAppListFailed------->".log()
    }
}

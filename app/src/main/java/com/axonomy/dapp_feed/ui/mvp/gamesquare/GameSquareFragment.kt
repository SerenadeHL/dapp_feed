package com.axonomy.dapp_feed.ui.mvp.gamesquare

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.RuntimeData
import com.axonomy.dapp_feed.bean.cash.ResultCashDailyIncomeBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultAnnouncementBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultAnnouncementItemBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppBean
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppItemBean
import com.axonomy.dapp_feed.extensions.show
import com.axonomy.dapp_feed.extensions.showRound
import com.axonomy.dapp_feed.utils.LoginUtils
import com.axonomy.dapp_feed.utils.RouterUtils
import kotlinx.android.synthetic.main.app_recycle_item_game_square.view.*
import kotlinx.android.synthetic.main.fragment_game_square.*
import kotlinx.android.synthetic.main.fragment_game_square.view.*
import me.serenadehl.base.base.mvpbase.MVPBaseFragment
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.extensions.visible

/**
 * 游戏广场页Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
@Route(path = Router.GAME_SQUARE_FRAGMENT)
class GameSquareFragment : MVPBaseFragment<IGameSquarePresenter>(), IGameSquareView {
    private var mPage = 0
    private var mPageSize = 3

    override fun layout() = R.layout.fragment_game_square

    override fun createPresenter() = GameSquarePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {

        mRootView.av_announcement.setOnItemClickListener { position, _ ->
            val item = mRootView.av_announcement.messages[position] as ResultAnnouncementItemBean
            RouterUtils.route(item.url)
        }

        mRootView.btn_login.setOnClickListener { ARouter.getInstance().build(Router.LOGIN_ACTIVITY).navigation() }

        mRootView.cv_coin.setOnClickListener {
            ARouter.getInstance().build(Router.TOTAL_COIN_COUNT_ACTIVITY).navigation()
        }
        mRootView.cv_cash.setOnClickListener {
            ARouter.getInstance().build(Router.TOTAL_CASH_COUNT_ACTIVITY).navigation()
        }

        //签到
        mRootView.tv_sign.setOnClickListener {
            //TODO 点我签到
            toast("点我签到")
        }
        //邀请好友
        mRootView.tv_invite.setOnClickListener {
            //TODO 邀请好友
            toast("邀请好友")
        }

        //隐藏Item上方分割线
        mRootView.cl_dapp1.v_top_divider.invisible()
        mRootView.cl_dapp2.v_top_divider.invisible()
        mRootView.cl_dapp3.v_top_divider.invisible()

        //充值
        mRootView.iv_first_charge.setOnClickListener {
            ARouter.getInstance()
                .build(Router.RECHARGE_ACTIVITY)
                .navigation()
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        if (LoginUtils.isLogin()) {
            mPresenter.getDailyCoinIncome()
            mPresenter.getDailyCashIncome()
            mRootView.iv_top_bg.setImageBitmap(null)
            mRootView.g_login.visible()
            mRootView.btn_login.invisible()
        } else {
            mRootView.iv_top_bg.show(RuntimeData.mResultCommonConfigurationBean?.homePublicHeader)
            mRootView.g_login.invisible()
            mRootView.btn_login.visible()
        }
    }

    override fun onStart() {
        super.onStart()
        mRootView.av_announcement.startFlipping()
    }

    override fun onStop() {
        super.onStop()
        mRootView.av_announcement.stopFlipping()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden)
            mRootView.av_announcement.stopFlipping()
        else
            mRootView.av_announcement.startFlipping()
    }

    private fun loadData() {
        mPresenter.getAnnouncement()
        mPresenter.getDAppList(mPage, mPageSize)
    }

    private fun enterDApp(item: ResultDAppItemBean) {
        ARouter.getInstance()
            .build(Router.DAPP_WEB_ACTIVITY)
            .withString(RouterParams.ID, item.pid)
            .withString(RouterParams.URL, item.url)
            .navigation()
    }

    override fun getAnnouncementSuccess(data: ResultAnnouncementBean?) {
        "getAnnouncementSuccess-------> $data".log()
        mRootView.av_announcement.startWithList(data?.items)
    }

    override fun getAnnouncementFailed() {
        "getAnnouncementFailed------->".log()
    }

    override fun getDAppListSuccess(data: ResultDAppBean?) {
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
                mRootView.cl_dapp1.setOnClickListener { enterDApp(this@apply) }
            }
            items[1].apply {
                mRootView.cl_dapp2.tv_name.text = title
                mRootView.cl_dapp2.iv_logo.showRound(logo, radiusDp)
                mRootView.cl_dapp2.tv_description.text = intro
                mRootView.cl_dapp2.tv_playing_count.text = String.format(getString(R.string.play_count), count)
                mRootView.cl_dapp2.setOnClickListener { enterDApp(this@apply) }
            }
            v_divider1.visible()
            items[2].apply {
                mRootView.cl_dapp3.tv_name.text = title
                mRootView.cl_dapp3.iv_logo.showRound(logo, radiusDp)
                mRootView.cl_dapp3.tv_description.text = intro
                mRootView.cl_dapp3.tv_playing_count.text = String.format(getString(R.string.play_count), count)
                mRootView.cl_dapp3.setOnClickListener { enterDApp(this@apply) }
            }
            v_divider2.visible()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getDAppListFailed() {
        "getDAppListFailed------->".log()
    }

    override fun getDailyCoinIncomeSuccess(data: ResultCoinBalanceBean?) {
        "getDailyCoinIncomeSuccess-------> $data".log()
        mRootView.tv_coin.text = data?.todayRevenue
    }

    override fun getDailyCoinIncomeFailed() {
        "getDailyCoinIncomeFailed------->".log()
    }

    override fun getDailyCashIncomeSuccess(data: ResultCashDailyIncomeBean?) {
        "getDailyCashIncomeSuccess-------> $data".log()
        mRootView.tv_cash.text = String.format(getString(R.string.money_with_symbol), data?.cash)
    }

    override fun getDailyCashIncomeFailed() {
        "getDailyCashIncomeFailed------->".log()
    }
}

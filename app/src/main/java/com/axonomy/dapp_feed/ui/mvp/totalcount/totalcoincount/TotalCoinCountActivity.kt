package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcoincount

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.adapter.recyclerview.CoinAdapter
import com.axonomy.dapp_feed.bean.coin.ResultCoinBalanceBean
import com.axonomy.dapp_feed.bean.coin.ResultCoinRecordsBean
import com.axonomy.dapp_feed.ui.mvp.totalcount.TotalCountParentActivity
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.activity_transfer.view.*
import kotlinx.android.synthetic.main.app_recycle_header_total_coin.view.*
import kotlinx.android.synthetic.main.app_recycle_header_total_coin.view.tv_balance
import me.serenadehl.base.extensions.log

/**
 * 金币资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
@Route(path = Router.TOTAL_COIN_COUNT_ACTIVITY)
class TotalCoinCountActivity : TotalCountParentActivity<ITotalCoinCountPresenter>(), ITotalCoinCountView {

    private val mAdapter by lazy { CoinAdapter() }

    override fun layout() = R.layout.activity_total_coin_count

    override fun createPresenter() = TotalCoinCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_transfer.setOnClickListener { ARouter.getInstance().build(Router.TRANSFER_COIN_ACTIVITY).navigation() }
        btn_recharge.setOnClickListener { ARouter.getInstance().build(Router.RECHARGE_ACTIVITY).navigation() }
        mHeader.iv_transferable.setOnClickListener {
            ARouter.getInstance()
                .build(Router.REVENUE_RULES_ACTIVITY)
                .navigation()
        }
        mHeader.iv_locked.setOnClickListener {
            ARouter.getInstance()
                .build(Router.REVENUE_RULES_ACTIVITY)
                .navigation()
        }
    }

    override fun getTopMarginViewId() = R.id.tv_balance

    override fun getTitleResId() = R.string.coin_assets

    override fun getHeaderResId() = R.layout.app_recycle_header_total_coin

    override fun getRecyclerView(): RecyclerView = rv_coin

    override fun getAdapter(): BaseQuickAdapter<*, BaseViewHolder> = mAdapter

    override fun getHeaderData() {
        mPresenter.getCoinBalance()
    }

    override fun loadData(refresh: Boolean) {
        super.loadData(refresh)
        mPresenter.getCoinRecords(mPage, mPageSize, refresh)
    }

    override fun getCoinBalanceSuccess(data: ResultCoinBalanceBean?) {
        "getCoinBalanceSuccess-------> $data".log()
        mHeader.tv_balance.text = data?.balance
        mHeader.tv_approximately.text = String.format(getString(R.string.approximately), data?.balanceCNY)
        mHeader.tv_transferable.text = data?.transferable
        mHeader.tv_locked.text = data?.locked
        mHeader.tv_today_obtain.text = data?.todayRevenue
    }

    override fun getCoinBalanceFailed() {
        "getCoinBalanceFailed------->".log()
    }

    override fun getCoinRecordsSuccess(data: ResultCoinRecordsBean?, refresh: Boolean) {
        "getCoinRecordsSuccess-------> $data".log()
        if (refresh) {
            mAdapter.setNewData(data?.items)
        } else {
            mAdapter.addData(data?.items ?: listOf())
            if (data?.hasMore() == true) {
                mAdapter.loadMoreComplete()
            } else {
                mAdapter.loadMoreEnd(true)
            }
        }
    }

    override fun getCoinRecordsFailed() {
        "getCoinRecordsFailed------->".log()
    }
}

package com.dong.dapp.ui.mvp.totalcount.totalcoincount

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dong.dapp.R
import com.dong.dapp.adapter.recyclerview.CoinAdapter
import com.dong.dapp.bean.coin.ResultCoinBalanceBean
import com.dong.dapp.bean.coin.ResultCoinRecordsBean
import com.dong.dapp.ui.mvp.totalcount.TotalCountParentActivity
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.app_recycle_header_total_coin.view.*
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.log

/**
 * 金币资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:27:16
 */
class TotalCoinCountActivity : TotalCountParentActivity<ITotalCoinCountPresenter>(), ITotalCoinCountView {

    private val mAdapter by lazy { CoinAdapter() }

    override fun layout() = R.layout.activity_total_coin_count

    override fun createPresenter() = TotalCoinCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (mHeader.tv_balance.layoutParams as ConstraintLayout.LayoutParams).topMargin += getStatusBarHeight() + dimen(R.dimen.L2)
    }

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
        mHeader.tv_balance.text = data?.balance
        mHeader.tv_approximately.text = String.format(getString(R.string.approximately), data?.evalValue)
        mHeader.tv_transferable.text = data?.transferable
        mHeader.tv_locked.text = data?.locked
        mHeader.tv_today_obtain.text = data?.todayRevenue
    }

    override fun getCoinBalanceFailed() {

    }

    override fun getCoinRecordsSuccess(data: ResultCoinRecordsBean?, refresh: Boolean) {
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

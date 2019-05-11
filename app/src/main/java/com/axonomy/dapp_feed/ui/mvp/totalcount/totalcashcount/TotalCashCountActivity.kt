package com.axonomy.dapp_feed.ui.mvp.totalcount.totalcashcount

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.adapter.recyclerview.CashAdapter
import com.axonomy.dapp_feed.bean.cash.ResultCashBalanceBean
import com.axonomy.dapp_feed.bean.cash.ResultCashRecordsBean
import com.axonomy.dapp_feed.ui.mvp.totalcount.TotalCountParentActivity
import kotlinx.android.synthetic.main.activity_total_cash_count.*
import kotlinx.android.synthetic.main.app_recycle_header_total_cash.view.*
import me.serenadehl.base.extensions.log

/**
 * 现金资产页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-23 16:26:19
 */
@Route(path = Router.TOTAL_CASH_COUNT_ACTIVITY)
class TotalCashCountActivity : TotalCountParentActivity<ITotalCashCountPresenter>(), ITotalCashCountView {

    private val mAdapter by lazy { CashAdapter() }

    override fun layout() = R.layout.activity_total_cash_count

    override fun createPresenter() = TotalCashCountPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_transfer.setOnClickListener { ARouter.getInstance().build(Router.TRANSFER_CASH_ACTIVITY).navigation() }
    }

    override fun getTopMarginViewId() = R.id.tv_balance

    override fun getTitleResId() = R.string.cash_assets

    override fun getHeaderResId() = R.layout.app_recycle_header_total_cash

    override fun getRecyclerView(): RecyclerView = rv_cash

    override fun getAdapter(): BaseQuickAdapter<*, BaseViewHolder> = mAdapter

    override fun getHeaderData() {
        mPresenter.getCashBalance()
    }

    override fun loadData(refresh: Boolean) {
        super.loadData(refresh)
        mPresenter.getCashRecords(mPage, mPageSize, refresh)
    }

    @SuppressLint("SetTextI18n")
    override fun getCashBalanceSuccess(data: ResultCashBalanceBean?) {
        "getCashBalanceSuccess-------> $data".log()
        val moneyWithSymbol = getString(R.string.money_with_symbol)
        mHeader.tv_balance.text = String.format(moneyWithSymbol, data?.balance)
        mHeader.tv_cumulative_gain.text = String.format(moneyWithSymbol, data?.totalRevenue)
        mHeader.tv_today_obtain.text = String.format(moneyWithSymbol, data?.todayRevenue)
    }

    override fun getCashBalanceFailed() {
        "getCashBalanceFailed------->".log()
    }

    override fun getCashRecordsSuccess(data: ResultCashRecordsBean?, refresh: Boolean) {
        "getCashRecordsSuccess-------> $data".log()
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

    override fun getCashRecordsFailed() {
        "getCashRecordsFailed------->".log()
    }
}

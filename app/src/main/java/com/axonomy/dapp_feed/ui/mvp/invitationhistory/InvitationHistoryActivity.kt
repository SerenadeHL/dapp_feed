package com.axonomy.dapp_feed.ui.mvp.invitationhistory

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.adapter.recyclerview.InvitationHistoryAdapter
import com.axonomy.dapp_feed.bean.invitation.ResultInvitationHistoryBean
import com.axonomy.dapp_feed.constant.Router
import kotlinx.android.synthetic.main.activity_invitation_history.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.app.SystemUtils

/**
 * 邀请记录页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-13 18:00:53
 */
@Route(path = Router.INVITATION_HISTORY_ACTIVITY)
class InvitationHistoryActivity : MVPBaseActivity<IInvitationHistoryPresenter>(), IInvitationHistoryView {
    protected var mPage = 0
    protected var mPageSize = 20

    private val mC2 by lazy { ContextCompat.getColor(this@InvitationHistoryActivity, R.color.C2) }

    private val mHeader by lazy {
        layoutInflater.inflate(
            R.layout.app_recycle_header_invitation_history,
            rv_invitation_history,
            false
        )
    }

    private val mAdapter by lazy { InvitationHistoryAdapter() }

    override fun layout() = R.layout.activity_invitation_history

    override fun createPresenter() = InvitationHistoryPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.invitation_history_detail)

        mAdapter.addHeaderView(mHeader)
        mAdapter.emptyView = generateEmptyView()
        mAdapter.setHeaderAndEmpty(true)
        rv_invitation_history.adapter = mAdapter

        loadData(true)
    }

    private fun generateEmptyView(): View {
        val emptyView = layoutInflater.inflate(R.layout.empty_layout, rv_invitation_history, false)
        val screenHeight = SystemUtils.getScreenHeight(this@InvitationHistoryActivity)
        val headerHeight = dimen(R.dimen.dp_invitation_history_header_height)
        val titleLayoutHeight = dimen(R.dimen.L2)
        val statusBarHeight = getStatusBarHeight()
        emptyView.layoutParams.height = screenHeight - titleLayoutHeight - headerHeight - statusBarHeight
        return emptyView
    }

    private fun loadData(refresh: Boolean) {
        if (refresh) mPage = 0 else mPage++
        mPresenter.getInvitationHistory(mPage, mPageSize, refresh)
    }

    override fun getInvitationHistorySuccess(data: ResultInvitationHistoryBean?, refresh: Boolean) {
        "getInvitationHistorySuccess-------> $data".log()
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

    override fun getInvitationHistoryFailed() {
        "getInvitationHistoryFailed------->".log()
    }

}

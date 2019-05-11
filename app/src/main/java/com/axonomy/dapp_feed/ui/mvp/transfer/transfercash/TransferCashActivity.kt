package com.axonomy.dapp_feed.ui.mvp.transfer.transfercash

import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.ui.mvp.transfer.TransferParentActivity

/**
 * 划转现金页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 11:55:26
 */
@Route(path = Router.TRANSFER_CASH_ACTIVITY)
class TransferCashActivity : TransferParentActivity() {

    override fun getTitleResId() = R.string.transfer_cash

    override fun getBalanceLabelResId() = R.string.cash_balance

    override fun showQuestionMark() = false

    override fun getQuotaResId() = R.string.cash_quota

    override fun getQuotaTextSPSize() = 14F
}
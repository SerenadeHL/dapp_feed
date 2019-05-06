package com.dong.dapp.ui.mvp.transfer

import com.dong.dapp.R

/**
 * 划转现金页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 11:55:26
 */
class TransferCashActivity : TransferParentActivity() {

    override fun getTitleResId() = R.string.transfer_cash

    override fun getBalanceLabelResId() = R.string.cash_balance

    override fun showQuestionMark() = false

    override fun getQuotaResId() = R.string.cash_quota

    override fun getQuotaTextSPSize() = 14F
}
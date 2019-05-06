package com.dong.dapp.ui.mvp.transfer

import com.dong.dapp.R

/**
 * 划转金币页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 11:55:26
 */
class TransferCoinActivity : TransferParentActivity() {

    override fun getTitleResId() = R.string.transfer_coin

    override fun getBalanceLabelResId() = R.string.coin_balance

    override fun showQuestionMark() = true

    override fun getQuotaResId() = R.string.coin_quota

    override fun getQuotaTextSPSize() = 16F
}
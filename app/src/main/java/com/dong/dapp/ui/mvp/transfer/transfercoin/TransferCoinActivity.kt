package com.dong.dapp.ui.mvp.transfer.transfercoin

import com.alibaba.android.arouter.facade.annotation.Route
import com.dong.dapp.R
import com.dong.dapp.constant.Router
import com.dong.dapp.ui.mvp.transfer.TransferParentActivity

/**
 * 划转金币页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 11:55:26
 */
@Route(path = Router.TRANSFER_COIN_ACTIVITY)
class TransferCoinActivity : TransferParentActivity() {

    override fun getTitleResId() = R.string.transfer_coin

    override fun getBalanceLabelResId() = R.string.coin_balance

    override fun showQuestionMark() = true

    override fun getQuotaResId() = R.string.coin_quota

    override fun getQuotaTextSPSize() = 16F
}
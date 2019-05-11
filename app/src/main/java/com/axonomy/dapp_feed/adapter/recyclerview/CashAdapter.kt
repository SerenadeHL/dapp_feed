package com.axonomy.dapp_feed.adapter.recyclerview

import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.bean.cash.ResultCashRecordsItemBean

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 20:27:47
 */
class CashAdapter : BaseQuickAdapter<ResultCashRecordsItemBean, BaseViewHolder>(R.layout.app_recycle_item_cash) {
    init {
        setOnItemClickListener { _, _, position ->
            //TODO设置数据
            ARouter.getInstance()
                .build(Router.TRANSFER_DETAIL_ACTIVITY)
                .withString(RouterParams.ID, getItem(position)?.id)
                .navigation()
        }
    }

    override fun convert(helper: BaseViewHolder?, item: ResultCashRecordsItemBean?) {
        helper?.apply {
            setText(R.id.tv_title, item?.title)
            setText(R.id.tv_time, item?.createAtStr)
            val symbol = mContext.getString(if (item?.changedType ?: 1 > 0) R.string.plus else R.string.minus)
            val money = String.format(mContext.getString(R.string.money_with_symbol), item?.amount)
            setText(R.id.tv_money, "$symbol $money")
            when (item?.doneStatus) {
                0 -> {
                    setText(R.id.tv_status, R.string.ongoing)
                    setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.C1))
                }
                1, 2 -> {
                    setText(R.id.tv_status, R.string.finished)
                    setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.C8))
                }
                3 -> {
                    setText(R.id.tv_status, R.string.failed)
                    setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.C5))
                }
            }
        }
    }
}
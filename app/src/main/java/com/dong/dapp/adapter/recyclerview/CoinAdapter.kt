package com.dong.dapp.adapter.recyclerview

import android.support.v4.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dong.dapp.R
import com.dong.dapp.bean.coin.ResultCoinRecordsItemBean
import com.dong.dapp.ui.mvp.transferdetail.TransferDetailActivity
import me.serenadehl.base.extensions.startActivity

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 20:27:47
 */
class CoinAdapter : BaseQuickAdapter<ResultCoinRecordsItemBean, BaseViewHolder>(R.layout.app_recycle_item_coin) {
    init {
        setOnItemClickListener { _, _, position ->
            //TODO设置数据
            mContext.startActivity<TransferDetailActivity>("data" to getItem(position))
        }
    }

    override fun convert(helper: BaseViewHolder?, item: ResultCoinRecordsItemBean?) {
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
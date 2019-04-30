package com.dong.dapp.adapter.recyclerview

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dong.dapp.R
import com.dong.dapp.bean.coin.ResultTotalCoinBean
import com.dong.dapp.ui.activity.TransferDetailActivity
import me.serenadehl.base.extensions.startActivity

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 20:27:47
 */
class CoinAdapter(activity: Activity) :
    BaseQuickAdapter<ResultTotalCoinBean, BaseViewHolder>(R.layout.app_recycle_item_coin) {
    init {
        setOnItemClickListener { _, _, position ->
            //TODO设置数据
            activity.startActivity<TransferDetailActivity>("data" to getItem(position))
        }
    }

    override fun convert(helper: BaseViewHolder?, item: ResultTotalCoinBean?) {
        helper?.apply {
            setText(R.id.tv_title, item?.title)
            setText(R.id.tv_time, item?.time)
            setText(R.id.tv_money, item?.money)
            setText(R.id.tv_status, item?.status)
        }
    }
}
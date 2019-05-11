package com.axonomy.dapp_feed.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.areacode.ResultAreaCodeItemBean

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-28 20:45:01
 */
class ChooseAreaCodeAdapter : BaseQuickAdapter<ResultAreaCodeItemBean, BaseViewHolder>(R.layout.app_recycle_item_choose_area_code) {

    private lateinit var mTitleIndex: Set<Int>

    fun setTitleIndex(titleIndex: Set<Int>) {
        mTitleIndex = titleIndex
    }

    override fun convert(helper: BaseViewHolder?, item: ResultAreaCodeItemBean?) {
        helper?.apply {
            setText(R.id.tv_area_code, "${item?.country} ${item?.code}")
            setVisible(R.id.v_divider, !mTitleIndex.contains(helper.layoutPosition))
        }
    }
}
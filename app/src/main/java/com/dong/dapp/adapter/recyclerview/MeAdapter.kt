package com.dong.dapp.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.dong.dapp.R
import com.dong.dapp.bean.me.MeBean

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 10:44:05
 */
class MeAdapter : BaseQuickAdapter<MeBean, BaseViewHolder>(R.layout.app_recycle_item_me) {
    companion object {
        private const val DIVIDER = 1
        private const val ITEM = 2
    }

    init {
        multiTypeDelegate = object : MultiTypeDelegate<MeBean>() {
            override fun getItemType(t: MeBean?): Int {
                return if (t?.isDivider == true) DIVIDER else ITEM
            }
        }

        multiTypeDelegate.apply {
            registerItemType(DIVIDER, R.layout.app_recycle_divider_me)
            registerItemType(ITEM, R.layout.app_recycle_item_me)
        }
    }

    override fun convert(helper: BaseViewHolder?, item: MeBean) {
        //分割线不处理
        if (item.isDivider) return

        val option = item.option!!//选项参数
        helper?.apply {
            setImageResource(R.id.iv_icon, option.resId)//设置图标
            setText(R.id.tv_title, option.title)//设置标题
            setText(R.id.tv_text, option.text)//设置箭头左侧文字
            setTextColor(R.id.tv_text, option.textColor)//设置箭头左侧文字颜色
            setVisible(R.id.iv_right_arrow, option.showRightArrow)//是否显示右箭头
            setVisible(R.id.v_divider, option.showDivider)//是否显示分割线
        }
    }
}
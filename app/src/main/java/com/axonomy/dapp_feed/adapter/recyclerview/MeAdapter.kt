package com.axonomy.dapp_feed.adapter.recyclerview

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.me.MeBean
import com.axonomy.dapp_feed.extensions.show

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
            getView<ImageView>(R.id.iv_icon).show(option.icon)//设置图标
            setText(R.id.tv_title, option.title)//设置标题
            setText(R.id.tv_text, option.text)//设置箭头左侧文字
            val color = if (option.textColor.isEmpty()) {
                ContextCompat.getColor(mContext, R.color.C2)
            } else {
                Color.parseColor(option.textColor)
            }
            setTextColor(R.id.tv_text, color)//设置箭头左侧文字颜色
            setGone(R.id.iv_right_arrow, option.showRightArrow)//是否显示右箭头
            setVisible(R.id.v_bottom_divider, option.showDivider)//是否显示分割线
        }
    }
}
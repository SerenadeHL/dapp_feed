package com.axonomy.dapp_feed.adapter.recyclerview

import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.bean.gamesquare.ResultDAppItemBean
import com.axonomy.dapp_feed.extensions.showRound

/**
 * Dapp列表Adapter
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 11:15:09
 */
class DAppsListAdapter : BaseQuickAdapter<ResultDAppItemBean, BaseViewHolder>(R.layout.app_recycle_item_game_square) {

    init {
        setOnItemClickListener { _, _, position ->
            val item = data[position]
            ARouter.getInstance()
                .build(Router.DAPP_WEB_ACTIVITY)
                .withString(RouterParams.ID, item.pid)
                .withString(RouterParams.URL, item.url)
                .navigation()
        }
    }

    override fun convert(helper: BaseViewHolder?, item: ResultDAppItemBean?) {
        helper?.apply {
            setText(R.id.tv_name, item?.title)
            setText(R.id.tv_description, item?.intro)
            setText(R.id.tv_playing_count, String.format(mContext.getString(R.string.play_count), item?.count))
            getView<ImageView>(R.id.iv_logo).showRound(item?.logo, 4F)
            setVisible(R.id.v_top_divider, (layoutPosition - headerLayoutCount) > 0)//隐藏第一个Item的分割线
        }
    }
}
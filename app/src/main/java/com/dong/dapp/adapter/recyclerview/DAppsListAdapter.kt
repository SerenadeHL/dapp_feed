package com.dong.dapp.adapter.recyclerview

import android.app.Activity
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dong.dapp.R
import com.dong.dapp.bean.gamesquare.ResultDAppListBean
import com.dong.dapp.extensions.showRound
import com.dong.dapp.ui.mvp.web.WebActivity

/**
 * Dapp列表Adapter
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 11:15:09
 */
class DAppsListAdapter(val activity: Activity) :
    BaseQuickAdapter<ResultDAppListBean.Item, BaseViewHolder>(R.layout.app_recycle_item_game_square) {

    init {
        setOnItemClickListener { _, _, position ->
            WebActivity.start(activity, data[position].pid, data[position].url)
        }
    }

    override fun convert(helper: BaseViewHolder?, item: ResultDAppListBean.Item?) {
        helper?.apply {
            setText(R.id.tv_name, item?.title)
            setText(R.id.tv_description, item?.intro)
            setText(R.id.tv_playing_count, String.format(activity.getString(R.string.play_count), item?.count))
            getView<ImageView>(R.id.iv_logo).showRound(item?.logo, 4F)
            setVisible(R.id.v_top_divider, (layoutPosition - headerLayoutCount) > 0)//隐藏第一个Item的分割线
        }
    }
}
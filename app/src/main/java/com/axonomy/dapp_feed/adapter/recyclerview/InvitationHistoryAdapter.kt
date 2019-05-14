package com.axonomy.dapp_feed.adapter.recyclerview

import android.support.v4.content.ContextCompat
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.invitation.ResultInvitationHistoryItemBean
import com.axonomy.dapp_feed.constant.KYCStatus
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 19:41:19
 */
class InvitationHistoryAdapter :
    BaseQuickAdapter<ResultInvitationHistoryItemBean, BaseViewHolder>(R.layout.app_recycle_item_invitation_history) {

    override fun convert(helper: BaseViewHolder?, item: ResultInvitationHistoryItemBean?) {
        helper?.apply {
            setText(R.id.tv_account, item?.account)
            setText(R.id.tv_time, item?.createTime)
            when (item?.status) {
                KYCStatus.VERIFY_PASSED -> {
                    setText(R.id.tv_status, R.string.verified)
                    setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.C6))
                }
                else -> {
                    setText(R.id.tv_status, R.string.unverified)
                    setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.color_F83131))
                }
            }
        }
    }
}
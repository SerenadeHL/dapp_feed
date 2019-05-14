package com.axonomy.dapp_feed.ui.mvp.invitationhistory

import com.axonomy.dapp_feed.bean.invitation.ResultInvitationHistoryBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-13 18:00:53
 */
class InvitationHistoryModel : MVPBaseModel(), IInvitationHistoryModel {

    override fun getInvitationHistory(page: Int, pageSize: Int): Observable<ResultInvitationHistoryBean?> {
        return RequestManager.getInvitationHistory(page,pageSize)
    }
}

package com.axonomy.dapp_feed.ui.mvp.invitationhistory

import com.axonomy.dapp_feed.bean.invitation.ResultInvitationHistoryBean
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.IBaseModel
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-13 18:00:53
 */
interface IInvitationHistoryView : IBaseView {
    fun getInvitationHistorySuccess(data: ResultInvitationHistoryBean?, refresh: Boolean)

    fun getInvitationHistoryFailed()
}

interface IInvitationHistoryPresenter : IBasePresenter {
    /**
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getInvitationHistory(page: Int, pageSize: Int, refresh: Boolean)
}

interface IInvitationHistoryModel : IBaseModel {
    /**
     * @param page 页数
     * @param pageSize 每页条数
     */
    fun getInvitationHistory(page: Int, pageSize: Int): Observable<ResultInvitationHistoryBean?>
}

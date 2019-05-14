package com.axonomy.dapp_feed.ui.mvp.invitationhistory

import com.axonomy.dapp_feed.bean.invitation.ResultInvitationHistoryBean
import com.axonomy.dapp_feed.network.BaseObserver
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.addDisposable

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-13 18:00:53
 */
class InvitationHistoryPresenter : MVPBasePresenter<IInvitationHistoryView, IInvitationHistoryModel>(),
    IInvitationHistoryPresenter {

    override fun createModel() = InvitationHistoryModel()

    override fun getInvitationHistory(page: Int, pageSize: Int, refresh: Boolean) {
        mModel.getInvitationHistory(page, pageSize)
            .addDisposable(mCompositeDisposable)
            .subscribe(object : BaseObserver<ResultInvitationHistoryBean?>() {
                override fun next(data: ResultInvitationHistoryBean?) {
                    mView.get()?.getInvitationHistorySuccess(data, refresh)
                }

                override fun error(error: Throwable) {
                    mView.get()?.getInvitationHistoryFailed()
                }
            })
    }
}

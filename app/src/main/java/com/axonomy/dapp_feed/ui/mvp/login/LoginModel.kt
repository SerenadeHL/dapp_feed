package com.axonomy.dapp_feed.ui.mvp.login

import com.axonomy.dapp_feed.bean.login.ResultLoginBean
import com.axonomy.dapp_feed.bean.login.ResultVerifyCodeBean
import com.axonomy.dapp_feed.network.RequestManager
import io.reactivex.Observable
import me.serenadehl.base.base.mvpbase.MVPBaseModel

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 10:59:26
 */
class LoginModel : MVPBaseModel(), ILoginModel {
    /**
     * 获取验证码
     * @param phone 手机号
     * @param areaCode 区号
     */
    override fun getVerifyCode(phone: String, areaCode: String): Observable<ResultVerifyCodeBean?> {
        return RequestManager.getVerifyCode(phone, areaCode)
    }

    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param invitationCode 邀请码
     */
    override fun login(verifyCode: String, fp: String, invitationCode: String): Observable<ResultLoginBean?> {
        return RequestManager.login(verifyCode,fp, invitationCode)
    }

}
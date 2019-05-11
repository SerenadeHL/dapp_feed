package com.axonomy.dapp_feed.network.api

import com.axonomy.dapp_feed.bean.login.RequestLoginBean
import com.axonomy.dapp_feed.bean.login.RequestVerifyCodeBean
import com.axonomy.dapp_feed.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 登录相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:09:40
 */
interface LoginApi {
    /**
     * 获取验证码
     * @param target 手机号或邮箱
     * @param type 手机号0 邮箱1
     * @param areaCode 手机区号
     */
    @POST("public/sms/sendcode")
    fun getVerifyCode(@Body requestBean: RequestVerifyCodeBean): Observable<BaseResponse>


    /**
     * 登录
     * @param verifyCode 验证码
     * @param fp getVerifyCode接口返回数据
     * @param invitation_code 邀请码
     */
    @POST("public/user/register")
    fun login(@Body requestBean: RequestLoginBean): Observable<BaseResponse>
}
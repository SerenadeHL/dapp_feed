package com.dong.dapp.network.api

import com.dong.dapp.bean.login.RequestLoginBean
import com.dong.dapp.bean.login.RequestVerifyCodeBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
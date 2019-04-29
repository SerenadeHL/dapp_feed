package com.dong.dapp.network.api

import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-29 18:29:38
 */
interface UserApi {
    /**
     * 获取用户信息
     */
    @POST("/api/v1/user/meta")
    fun getUserInfo(): Observable<BaseResponse>
}
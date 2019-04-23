package com.dong.dapp.network.api

import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 17:09:39
 */
interface TestApi {
    @GET("https://dappapi.axonomy.pro/public/common/test")
    fun test(@Query("test") test: String): Observable<BaseResponse>
}
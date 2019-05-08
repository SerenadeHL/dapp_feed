package com.dong.dapp.network.api

import com.dong.dapp.bean.multipage.RequestMultiPageBean
import com.dong.dapp.network.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 15:35:33
 */
interface CommonApi {

    /**
     * 获取通用配置
     */
    @POST("public/common/dappfeed")
    fun getCommonConfiguration(): Observable<BaseResponse>

    /**
     * 获取版本更新信息
     */
    @POST("public/common/version/2")
    fun getUpdateInfo(): Observable<BaseResponse>

    /**
     * 获取公告
     */
    @GET("api/df/notices")
    fun getAnnouncement(): Observable<BaseResponse>

    /**
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    @POST("public/dapp/list")
    fun getDAppList(@Body requestBean: RequestMultiPageBean): Observable<BaseResponse>

}

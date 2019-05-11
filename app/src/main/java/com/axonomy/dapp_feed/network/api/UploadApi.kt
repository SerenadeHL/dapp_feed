package com.axonomy.dapp_feed.network.api

import com.axonomy.dapp_feed.network.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * 上传文件相关API
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-08 17:28:50
 */
interface UploadApi {

    /**
     * 获取oss客户端上传权限（限制版）
     */
    @GET("api/oss/sts/limit")
    fun getOSSUploadPermission(): Observable<BaseResponse>

    /**
     * 上传文件
     */
    //TODO 更改接口
    @Multipart
    @POST("api/uploadpublic")
    fun uploadFile(@Part body: MultipartBody.Part): Observable<BaseResponse>
}
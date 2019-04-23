package com.dong.dapp.network.api

import com.dong.dapp.bean.kyc.*
import com.dong.dapp.bean.wallet.*
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
     * 获取DApp列表
     * @param page 页数
     * @param pageSize 每页条数
     */
    @GET("/dapps")
    fun getDApps(@Field("page") page: Int, @Field("page_size") pageSize: Int): Observable<BaseResponse>

    /**
     * 上传文件
     */
    @Multipart
    @POST("/api/uploadpublic")
    fun uploadFile(@Part body: MultipartBody.Part): Observable<BaseResponse>
}

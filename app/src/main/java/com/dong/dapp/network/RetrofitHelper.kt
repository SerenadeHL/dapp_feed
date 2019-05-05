package com.dong.dapp.network

import com.dong.dapp.DAppApplication
import com.dong.dapp.extensions.getGson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 15:36:27
 */
object RetrofitHelper {
    private const val DEFAULT_TIMEOUT = 10L
    private const val BASE_URL_RELEASE = "https://dappapi.axonomy.pro/"
    private const val BASE_URL_DEBUG = "https://dappapi.axonomy.pro/"

//    private const val BASE_URL_RELEASE = "http://192.168.216.172:9129/"
//    private const val BASE_URL_DEBUG = "http://192.168.216.172:9129/"

    private val mRetrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(InterceptorUtil.getHeaderInterceptor())
            .addInterceptor(InterceptorUtil.getLogInterceptor())//添加日志拦截器
            .build()

        Retrofit.Builder()
            .baseUrl(if (DAppApplication.isDebug()) BASE_URL_DEBUG else BASE_URL_RELEASE)//设置项目总地址
            .addConverterFactory(GsonConverterFactory.create())//添加Gson解析工厂
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RxJava2转换器
            .client(client)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}

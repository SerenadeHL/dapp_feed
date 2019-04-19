package com.dong.dapp.network

import android.util.Log
import com.dong.dapp.BuildConfig
import com.dong.dapp.Constant
import com.dong.dapp.DAppApplication
import com.dong.dapp.bean.wallet.SystemInfoBean
import com.dong.dapp.extensions.toJson
import com.dong.dapp.utils.NetworkUtils
import com.dong.dapp.utils.NetworkUtils.getNetworkType
import com.dong.dapp.utils.SystemUtils
import me.serenadehl.base.extensions.TAG
import me.serenadehl.base.utils.sharedpre.SPUtil
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:22:08
 */
object InterceptorUtil {
    //日志拦截器
    fun getLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i(TAG, "" + message) })
            .setLevel(if (DAppApplication.isDebug()) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)//设置打印数据的级别
    }

    fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            //旧的请求
            val oldRequest = chain.request()

            var requestBody = oldRequest.body()
            var url = oldRequest.url()
            if (oldRequest.method() == "POST" && requestBody is FormBody) {
                val bodyBuilder = FormBody.Builder()
                for (i in 0 until requestBody.size()) {
                    bodyBuilder.addEncoded(requestBody.encodedName(i), requestBody.encodedValue(i))
                }
                requestBody = bodyBuilder
                    .addEncoded(Constant.REQUEST_ID, UUID.randomUUID().toString().trim().replace("-".toRegex(), ""))
                    .build();
            } else if (oldRequest.method() == "GET") {
                url = oldRequest.url().newBuilder().scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter(
                        Constant.REQUEST_ID,
                        UUID.randomUUID().toString().trim().replace("-".toRegex(), "")
                    )
                    .build()
            }

            val systemInfoBean = SystemInfoBean(
                "android",
                SystemUtils.getSystemVersion(),
                SystemUtils.getAppVersionName(),
                BuildConfig.FLAVOR,
                SystemUtils.getIMEI(),
                SystemUtils.getMacAddress(),
                getNetworkType(),
                SystemUtils.getScreenWidth().toString(),
                SystemUtils.getScreenHeight().toString(),
                SystemUtils.getDeviceBrand(),
                SystemUtils.getDeviceModel()
            )

            //新的请求
            val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), requestBody)
                .url(url)
                .addHeader(Constant.AUTHORIZATION, SPUtil.getString(Constant.TOKEN, ""))
                .addHeader(Constant.REQUEST_VERSION, "1")
                .addHeader(Constant.TERMINAL, "android")
//                    .addHeader(Constant.TMID,)
                .addHeader(Constant.APP_INFO, systemInfoBean.toJson())
                .build()
            chain.proceed(newRequest)
        }
    }

    private fun getNetworkType(): String {
        return when (NetworkUtils.getNetworkType()) {
            NetworkUtils.NETWORK_WIFI -> "0"
            NetworkUtils.NETWORK_2G -> "2"
            NetworkUtils.NETWORK_3G -> "3"
            NetworkUtils.NETWORK_4G -> "4"
            else -> "-1"
        }
    }
}

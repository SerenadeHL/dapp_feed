package com.dong.dapp.network

import android.util.Log
import com.dong.dapp.BuildConfig
import com.dong.dapp.Constant
import com.dong.dapp.DAppApplication
import com.dong.dapp.bean.others.SystemInfoBean
import com.dong.dapp.extensions.toJson
import com.dong.dapp.utils.AESUtils
import com.dong.dapp.utils.NetworkUtils
import com.dong.dapp.utils.RSAUtils
import com.dong.dapp.utils.SystemUtils
import me.serenadehl.base.extensions.TAG
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.sharedpre.SPUtil
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.BufferedSink
import okio.ByteString
import java.nio.charset.Charset
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

            val method = oldRequest.method()
            var requestBody = oldRequest.body()
            var url = oldRequest.url()

            if (method == "POST") {
                requestBody?.writeTo(object : BaseSink() {
                    override fun write(byteString: ByteString): BufferedSink {
                        //获取原始json数据
                        val params = byteString.string(Charset.forName("utf-8"))
                        "原始数据---------> $params".log()
                        val encryptedParams = encryptParams(params)
                        "加密数据---------> $encryptedParams".log()
                        val mediaType = MediaType.parse("application/json; charset=utf-8")
                        val json = generateNewParams(encryptedParams, method)
                        "提交参数---------> $json".log()
                        requestBody = RequestBody.create(mediaType, json)
                        return this
                    }
                })
            } else if (method == "GET") {
                val params = dealGetParams(url)
                "原始数据---------> $params".log()
                val encryptedParams = encryptParams(params)
                "加密数据---------> $encryptedParams".log()
                val query = generateNewParams(encryptedParams, method)
                "提交参数---------> $query".log()
                url = url.newBuilder()
                    .scheme(url.scheme())
                    .host(url.host())
                    .query(query)
                    .build()
            }

            val systemInfoBean = SystemInfoBean(
                Constant.OS,
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
                .addHeader(Constant.REQUEST_ID, UUID.randomUUID().toString().trim().replace("-".toRegex(), ""))
                .addHeader(Constant.REQUEST_VERSION, "1")
                .addHeader(Constant.LANGUAGE, Constant.CN)
                .addHeader(Constant.TERMINAL, Constant.OS)
                .addHeader(Constant.APP_INFO, systemInfoBean.toJson())
                .build()
            chain.proceed(newRequest)
        }
    }

    /**
     * 获取网络类型
     */
    private fun getNetworkType(): String {
        return when (NetworkUtils.getNetworkType()) {
            NetworkUtils.NETWORK_WIFI -> "0"
            NetworkUtils.NETWORK_2G -> "2"
            NetworkUtils.NETWORK_3G -> "3"
            NetworkUtils.NETWORK_4G -> "4"
            else -> "-1"
        }
    }

    /**
     * GET请求参数拼接RequestId
     */
    private fun dealGetParams(url: HttpUrl): String {
        val map = mutableMapOf<String, String>()
        url.queryParameterNames().forEach {
            try {
                //TODO 寻找更好的解决办法
                map[it] = url.queryParameterValues(it)[0]
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return map.toJson()
    }

    /**
     * 加密参数
     */
    private fun encryptParams(params: String): String {
        val data = AESUtils.encrypt(params, AESUtils.generateKey(), AESUtils.generateIv())
        val encryptKey = RSAUtils.encrypt("${AESUtils.generateKey()}${AESUtils.generateIv()}")
        return "$data.$encryptKey".replace("\n", "")
    }

    /**
     * 生成新的请求参数
     */
    private fun generateNewParams(encryptedParams: String, method: String): String {
        return when (method) {
            "GET" -> "${Constant.API_DATA_NAME}=$encryptedParams&" +
                    "${Constant.API_VERSION_NAME}=${Constant.API_VERSION}"
            "POST" -> mapOf(
                Constant.API_VERSION_NAME to Constant.API_VERSION,
                Constant.API_TYPE_NAME to Constant.API_TYPE,
                Constant.API_DATA_NAME to encryptedParams
            ).toJson()
            else -> ""
        }
    }
}

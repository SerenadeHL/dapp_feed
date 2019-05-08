package com.dong.dapp.network

import android.util.Log
import com.dong.dapp.BuildConfig
import com.dong.dapp.constant.Constant
import com.dong.dapp.DAppApplication
import com.dong.dapp.bean.others.SystemInfoBean
import com.dong.dapp.utils.AESUtils
import com.dong.dapp.utils.NetworkUtils
import com.dong.dapp.utils.RSAUtils
import com.dong.dapp.utils.SystemUtils
import me.serenadehl.base.extensions.TAG
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toJson
import me.serenadehl.base.utils.sharedpre.SPUtil
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONObject
import java.nio.charset.Charset
import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:22:08
 */
object InterceptorUtil {
    private val mSystemVersion by lazy { SystemUtils.getSystemVersion() }
    private val mAppVersionName by lazy { SystemUtils.getAppVersionName() }
    private val mIMEI by lazy { SystemUtils.getIMEI() }
    private val mMacAddress by lazy { SystemUtils.getMacAddress() }
    private val mScreenWidth by lazy { SystemUtils.getScreenWidth().toString() }
    private val mScreenHeight by lazy { SystemUtils.getScreenHeight().toString() }
    private val mDeviceBrand by lazy { SystemUtils.getDeviceBrand() }
    private val mDeviceModel by lazy { SystemUtils.getDeviceModel() }

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
                requestBody = dealPost(requestBody)
            } else if (method == "GET") {
                url = dealGet(url)
            }

            val systemInfoBean = SystemInfoBean(
                Constant.OS,
                mSystemVersion,
                mAppVersionName,
                BuildConfig.FLAVOR,
                mIMEI,
                mMacAddress,
                getNetworkType(),
                mScreenWidth,
                mScreenHeight,
                mDeviceBrand,
                mDeviceModel
            )

            //新的请求
            val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), requestBody)
                .url(url)
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
     * 处理GET请求流程
     */
    private fun dealGet(url: HttpUrl): HttpUrl {
        val jsonObject = JSONObject()
        url.queryParameterNames().forEach {
            try {
                //TODO 寻找更好的解决办法
                jsonObject.put(it, url.queryParameterValues(it)[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val params = jsonObject.toString()
        val query = dealGetParams(params)
        return url.newBuilder()
            .scheme(url.scheme())
            .host(url.host())
            .query(query)
            .build()
    }

    /**
     * 处理POST请求流程
     */
    private fun dealPost(requestBody: RequestBody?): RequestBody? {
        val buffer = Buffer()
        requestBody?.writeTo(buffer)
        var params = buffer.readString(Charset.forName("utf-8"))
        if (params == null || params.isEmpty()) {
            params = JSONObject().toString()
        }
        return dealPostParams(params)
    }

    /**
     * 处理GET请求参数
     */
    private fun dealGetParams(params: String): String {
        "原始数据---------> $params".log()
        //添加Token
        val paramsWithToken = addToken(params)
        "添加Token后的数据---------> $paramsWithToken".log()
        val encryptedParams = encryptParams(paramsWithToken)
        "加密数据---------> $encryptedParams".log()
        val query = generateNewParams(encryptedParams, true)
        "提交参数---------> $query".log()
        return query
    }

    /**
     * 处理POST请求参数
     */
    private fun dealPostParams(params: String): RequestBody {
        "原始数据---------> $params".log()
        //添加Token
        val paramsWithToken = addToken(params)
        "添加Token后的数据---------> $paramsWithToken".log()
        //加密
        val encryptedParams = encryptParams(paramsWithToken)
        "加密数据---------> $encryptedParams".log()
        //构建新的RequestBody
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val json = generateNewParams(encryptedParams, false)
        "提交参数---------> $json".log()
        return RequestBody.create(mediaType, json)
    }

    /**
     * 添加Token
     */
    private fun addToken(params: String): String {
        val token = SPUtil.getString(Constant.TOKEN, "")
        return if (token.isEmpty())
            params
        else
            JSONObject(params).put(Constant.TOKEN, token).toString()
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
    private fun generateNewParams(encryptedParams: String, isGet: Boolean): String {
        return if (isGet)
            "${Constant.API_DATA_NAME}=$encryptedParams&${Constant.API_VERSION_NAME}=${Constant.API_VERSION}"
        else
            JSONObject()
                .put(Constant.API_VERSION_NAME, Constant.API_VERSION)
                .put(Constant.API_TYPE_NAME, Constant.API_TYPE)
                .put(Constant.API_DATA_NAME, encryptedParams)
                .toString()
    }
}

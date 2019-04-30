package com.dong.dapp.jsapi

import android.app.AlertDialog
import android.text.TextUtils
import android.webkit.JavascriptInterface
import com.dong.dapp.Constant
import com.dong.dapp.bean.tronweb.TronDAppRequest
import com.dong.dapp.bean.wallet.TronSignBean
import com.dong.dapp.bean.wallet.UserInfoBean
import com.dong.dapp.extensions.*
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.RequestManager
import com.dong.dapp.utils.UserInfoUtils
import com.google.gson.Gson
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.app.AppManager
import wendu.dsbridge.CompletionHandler

import java.util.ArrayList

/**
 * 波场相关的DApp js与原生交互
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-12 16:30:12
 */
class TronPayApi {

    companion object {
        const val JS_Bridge_Tag = "tronPay_js"
    }

    private var mAlertDialog: AlertDialog.Builder? = null

    @JavascriptInterface
    fun getTronAccount(msg: Any, handler: CompletionHandler<String>) {
        "====getTronAccount====".log()

        try {
            val userInfoBean = UserInfoUtils.getUserInfo()
            "====userInfoResBean====${userInfoBean?.log()}"
            handler.complete(userInfoBean?.publicKey)
        } catch (e: Exception) {
            e.printStackTrace()
            handler.complete(null)
        }
    }

    @JavascriptInterface
    fun requestSignature(msg: Any, handler: CompletionHandler<String>) {
        "====requestSignature====$msg".log()

        val transaction = msg.toString()

        //判断传入的是否是 hash 字符串,签署授权消息
        val userInfoBean = UserInfoUtils.getUserInfo()
        if (!transaction.isJson() || transaction.startsWith("0x")) {
            //明文不需要用户授权
            requestApiSignMessage(userInfoBean, handler, transaction)
        } else {
            //密文需要用户授权
            if (mAlertDialog == null) {
                mAlertDialog = AlertDialog.Builder(AppManager.instance.currentActivity)
                    .setMessage("DApp请求Tron账户进行签名操作，是否继续？\n$transaction")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        requestApiSignTxID(userInfoBean, handler, transaction)
                        mAlertDialog = null
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        val sign = "Cancel"
                        handler.complete(sign)
                        mAlertDialog = null
                    }
                mAlertDialog!!.show()
            }
        }
    }

    // TODO 这里为了方便将网络请求写在了一起，你们项目可以根据需要进行抽离
//    private fun requestApiSignature(handler: CompletionHandler<String>, transaction: String) {
//        try {
//            val userInfoBean = UserInfoUtils.getTronUserInfo()
//            "====userInfoResBean====${userInfoBean?.toString()}".log()
//
//            //判断传入的是否是 hash 字符串,签署授权消息
//            if (!transaction.isJson() || transaction.startsWith("0x")) {
//                requestApiSignMessage(userInfoBean, handler, transaction)
//            } else {
//                requestApiSignTxID(userInfoBean, handler, transaction)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            handler.complete(null)
//        }
//
//    }

    /**
     * 签署普通文本&hex
     * TODO 这里处理数据格式为，明文数据或者十六进制的数据
     * TODO 一般明文数据是用来做信任认证的，DApp好像是用来做唯一性标识的
     * TODO 这里的是图层最好与签署合约调用的区分开来
     */
    private fun requestApiSignMessage(
        userInfoBean: UserInfoBean?,
        handler: CompletionHandler<String>,
        transaction: String
    ) {
        "==requestApiSignMessage==$transaction".log()

        // if (transaction.startsWith("0x") && transaction.length() > 64) {
        //     Log.d(TAG, "==tronSignResBean== with hex" + transaction);
        //     tronSignReqBean.setType(TronSignReqBean.Type.HEX.getValue());
        // }

        RequestManager
            .getTronSign(userInfoBean?.publicKey ?: "", transaction, Constant.TEXT)
            .subscribe(object : BaseObserver<TronSignBean?>() {
                override fun next(data: TronSignBean?) {
                    var sign = data?.signature ?: ""
                    if (TextUtils.isEmpty(sign)) return
                    if (!sign.startsWith("0x")) sign = "0x$sign"
                    "==requestApiSignMessage==$sign".log()
                    val signature = ArrayList<String>()
                    signature.add(sign)
                    val json = signature.toJson()
                    handler.complete(json)
                }

                override fun error(error: Throwable) {
                    error.printStackTrace()
                }

                override fun complete() {
                    "==onComplete==".log()
                }
            })
    }

    /**
     * 签署智能合约 TxID
     * TODO 这里处理合约调用的数据格式，即会接收到json格式数据，通过反序列化获取java对象来使用
     */
    private fun requestApiSignTxID(
        userInfoBean: UserInfoBean?,
        handler: CompletionHandler<String>,
        transaction: String
    ) {

        //DApp传入的json，转换成java bean
        val tronDAppRequest = Gson().fromJson(transaction, TronDAppRequest::class.java)

        RequestManager
            .getTronSign(userInfoBean?.publicKey ?: "", tronDAppRequest?.txID ?: "", Constant.HEX)
            .subscribe(object : BaseObserver<TronSignBean?>() {
                override fun next(data: TronSignBean?) {
                    "==tronSignResBean==${data.toString()}".log()

                    //这里需要注意，需要使用传入的数据设置一个signature集合
                    val signature = ArrayList<String>()
                    data?.signature?.apply {
                        signature.add(this@apply)
                    }
                    tronDAppRequest.signature = signature

                    val json = tronDAppRequest.toJson()
                    //将数据转成JSON回传给网页
                    handler.complete(json)
                }

                override fun error(error: Throwable) {
                    error.printStackTrace()
                }

                override fun complete() {
                    "==onComplete==".log()
                }
            })
    }
}

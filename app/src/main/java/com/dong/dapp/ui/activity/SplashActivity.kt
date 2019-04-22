package com.dong.dapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.dong.dapp.network.DAppRequest
import com.dong.dapp.utils.AESUtils
import com.dong.dapp.utils.RSAUtils
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.log
import okhttp3.RequestBody


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 13:11:20
 */
class SplashActivity : BaseActivity() {

    override fun layout() = com.dong.dapp.R.layout.activity_splash

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        Observable.timer(3, TimeUnit.SECONDS)
//            .subscribe {
//                startActivity<MainActivity>()
//                finish()
//            }

//        val content = "test=1"
//        val key = AESUtils.generateKey()
//        val iv = AESUtils.generateIv()
//        val aesEncrypt = AESUtils.encrypt(content, key, iv)
//        val byteArray = RSAUtils.encrypt("$key$iv")
//        val rsaResult = byteArray
//        "加密数据=$aesEncrypt".log()
//        "RSA加密结果***$rsaResult***".log()
//        val jiami = "$aesEncrypt.$rsaResult"
//        val json = "{\n" +
//                "\t\"version\": 1,\n" +
//                "\t\"data\": \"${jiami}\",\n" +
//                "\t\"type\": 0\n" +
//                "}"
        DAppRequest.test("1")
            .subscribe {
                "结果=====${it.data}".log()
            }
    }

}
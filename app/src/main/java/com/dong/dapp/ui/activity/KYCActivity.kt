package com.dong.dapp.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.dong.dapp.R
import com.dong.dapp.utils.KYCUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import me.serenadehl.base.base.BaseActivity

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:44:33
 */
class KYCActivity : BaseActivity() {

    override fun layout() = R.layout.activity_kyc

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        //TODO 添加按钮触发KYC
        RxPermissions(this@KYCActivity)
            .request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .subscribe { granted ->
                if (granted) {
                    KYCUtils.start()
                } else {
                    //TODO 用户拒绝了权限给与提醒
                }
            }
    }

}

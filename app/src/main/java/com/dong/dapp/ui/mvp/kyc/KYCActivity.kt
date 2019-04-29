package com.dong.dapp.ui.mvp.kyc

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.dong.dapp.R
import com.dong.dapp.bean.kyc.ResultIdCardNumberAvailableBean
import com.dong.dapp.utils.KYCUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_kyc.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.toast

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:44:33
 */
class KYCActivity : MVPBaseActivity<IKYCPresenter>(), IKYCView {

    override fun layout() = R.layout.activity_kyc

    override fun createPresenter() = KYCPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        btn_verify.setOnClickListener {
            val idCardNumber = et_id_card_number.text.toString()
            //TODO 校验身份证是否合法
            mPresenter.checkIdCarNumber(idCardNumber)
        }

    }


    @SuppressLint("CheckResult")
    override fun checkIdCarNumberSuccess(bean: ResultIdCardNumberAvailableBean?) {
        when (bean?.isRight) {
            0 -> {
                toast(R.string.id_card_has_been_used)
            }
            2 -> {
                toast(R.string.you_have_passed_kyc)
            }
            else -> {
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
    }

    override fun checkIdCarNumberFailed() {

    }
}

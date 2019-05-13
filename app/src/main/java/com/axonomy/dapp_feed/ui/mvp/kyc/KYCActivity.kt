package com.axonomy.dapp_feed.ui.mvp.kyc

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.RuntimeData
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.bean.kyc.ResultIdCardNumberAvailableBean
import com.axonomy.dapp_feed.constant.KYCStatus
import com.axonomy.dapp_feed.exception.BaseException
import com.axonomy.dapp_feed.utils.DialogUtils
import com.axonomy.dapp_feed.utils.KYCUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_kyc.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast
import org.bouncycastle.jce.interfaces.MQVPrivateKey

/**
 * KYC页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 11:44:33
 */
@Route(path = Router.KYC_ACTIVITY)
class KYCActivity : MVPBaseActivity<IKYCPresenter>(), IKYCView {
    private lateinit var mProgressDialog: ProgressDialog
    private val mC2 by lazy { ContextCompat.getColor(this@KYCActivity, R.color.C2) }

    override fun layout() = R.layout.activity_kyc

    override fun createPresenter() = KYCPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.kyc_identification)

        btn_verify.setOnClickListener {
            val idCardNumber = et_id_card_number.text.toString()
            //TODO 校验身份证是否合法
            mPresenter.checkIdCarNumber(idCardNumber)
        }

        et_id_card_number.requestFocus()
    }

    private fun showProgressDialog() {
        mProgressDialog = DialogUtils.showProgressDialog(
            this,
            getString(R.string.kyc_verifing),
            getString(R.string.please_complete_kyc_verification)
        )
    }

    private fun hideProgressDialog() {
        if (::mProgressDialog.isInitialized) mProgressDialog.dismiss()
    }

    @SuppressLint("CheckResult")
    override fun checkIdCarNumberSuccess(bean: ResultIdCardNumberAvailableBean?) {
        "checkIdCarNumberSuccess-------> isRight=${bean?.isRight}".log()
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
                            showProgressDialog()
                            KYCUtils.start(success = {
                                hideProgressDialog()
                                toast(R.string.kyc_verify_success)
                                RuntimeData.mResultUserInfoBean?.kycStatus = KYCStatus.IN_VERIFYING
                                finish()
                            }, fail = { error ->
                                hideProgressDialog()
                                if (error is BaseException) {
                                    "BaseExceptionErrorCode=${error.code}".log()
                                    "BaseExceptionErrorMsg=${error.msg}".log()
                                    when (error.code) {
                                        KYCUtils.ID_CARD_HAS_BEEN_USED -> toast(R.string.id_card_has_been_used)
                                        KYCUtils.KYC_HAVE_BEEN_FINISHED -> toast(R.string.you_have_passed_kyc)
                                    }
                                } else {
                                    toast(R.string.unknown_error)
                                }
                            })
                        } else {
                            //TODO 用户拒绝了权限给与提醒
                        }
                    }
            }
        }
    }

    override fun checkIdCarNumberFailed() {
        "checkIdCarNumberFailed------->".log()
    }
}

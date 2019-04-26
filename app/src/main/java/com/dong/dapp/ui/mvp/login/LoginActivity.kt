package com.dong.dapp.ui.mvp.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.Constant
import com.dong.dapp.R
import com.dong.dapp.bean.login.ResultLoginBean
import com.dong.dapp.bean.login.ResultVerifyCodeBean
import com.dong.dapp.extensions.toJson
import com.dong.dapp.ui.mvp.chooseareacode.ChooseAreaCodeActivity
import com.dong.dapp.utils.AESUtils
import com.dong.dapp.utils.LoginUtils
import com.dong.dapp.utils.RSAUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.showKeyboard
import me.serenadehl.base.extensions.startActivity
import java.util.concurrent.TimeUnit

/**
 * 登录页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 10:57:09
 */
class LoginActivity : MVPBaseActivity<ILoginPresenter>(), ILoginView {
    private val mC2 by lazy { ContextCompat.getColor(this@LoginActivity, R.color.C2) }
    private val mCountDownTime = 120L
    private val mCompositeDisposable by lazy { CompositeDisposable() }

    private var mFp = ""
    private var mAreaCode = Constant.AREA_CODE_CN
    private val mRequestCode = 100

    override fun layout() = R.layout.activity_login

    override fun createPresenter() = LoginPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setImageResource(R.mipmap.close)
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.login)

        tv_area_code.text = mAreaCode

        //一进入页面让手机号输入框获取焦点并弹出软键盘
        et_phone_number.requestFocus()

        tv_area_code.setOnClickListener {
            startActivityForResult(Intent(this@LoginActivity, ChooseAreaCodeActivity::class.java), mRequestCode)
        }

        btn_get_verify_code.setOnClickListener {
            val areaCode = tv_area_code.text.toString()
            val phoneNumber = et_phone_number.text.toString()
            //TODO 校验手机号
            //获取验证码并开始倒计时
            mPresenter.getVerifyCode(phoneNumber, areaCode)
            startCountDown()
            et_verify_code.requestFocus()
        }

        btn_login.setOnClickListener {
            val verifyCode = et_verify_code.text.toString()
            val invitationCode = et_invitation_code.text.toString()

            //TODO 校验手机号、验证码、mFp
            mPresenter.login(verifyCode, mFp, invitationCode)

//            val params = mapOf(
//                "fp" to "74bc0c8a46b5a4f50401a671b879463b",
//                "invitation_code" to "",
//                "verification_code" to "711856"
//            ).toJson()
//            "params---------> $params".log()
//            val data = AESUtils.encrypt(params, AESUtils.generateKey(), AESUtils.generateIv())
//            val encryptKey = RSAUtils.encrypt("${AESUtils.generateKey()}${AESUtils.generateIv()}")
//            val encryptedParams = "$data.$encryptKey".replace("\n", "")
//            val json = mapOf(
//                Constant.API_VERSION_NAME to Constant.API_VERSION,
//                Constant.API_TYPE_NAME to Constant.API_TYPE,
//                Constant.API_DATA_NAME to encryptedParams
//            ).toJson()
//            "json---------> $json".log()
        }

        tv_agreement.text = "登录暨同意《用户协议书》"
    }

    override fun getVerifyCodeSuccess(verifyCodeBean: ResultVerifyCodeBean?) {
        mFp = verifyCodeBean?.fp ?: ""
    }

    override fun getVerifyCodeFailed() {

    }

    override fun loginSuccess(loginBean: ResultLoginBean?) {
        LoginUtils.saveLoginTag(loginBean?.token ?: "")
        finish()
    }

    override fun loginFailed() {

    }

    /**
     * 开始倒计时
     */
    private fun startCountDown() {
        mCompositeDisposable.add(
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mCountDownTime + 1)
                .map { mCountDownTime - it }
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .doOnSubscribe { btn_get_verify_code.isEnabled = false }
                .doOnComplete {
                    btn_get_verify_code.isEnabled = true
                    btn_get_verify_code.setText(R.string.get_verify_code)
                }
                .subscribe { btn_get_verify_code.text = String.format(getString(R.string.left_seconds), it) }
        )
    }

    override fun onDestroy() {
        //关闭页面时取消倒计时
        mCompositeDisposable.dispose()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mRequestCode && resultCode == Activity.RESULT_OK) {
            mAreaCode = data?.getStringExtra(Constant.AREA_CODE) ?: Constant.AREA_CODE_CN
        }
    }
}

package com.dong.dapp.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dong.dapp.constant.Constant
import com.dong.dapp.R
import com.dong.dapp.constant.Router
import com.dong.dapp.constant.RouterParams
import com.dong.dapp.bean.me.ResultUserInfoBean
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.toast

/**
 * 个人信息页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-06 17:24:16
 */
@Route(path = Router.PERSONAL_INFO_ACTIVITY)
class PersonalInfoActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RouterParams.DATA)
    var mUserInfo: ResultUserInfoBean? = null

    private val mC1 by lazy { ContextCompat.getColor(this@PersonalInfoActivity, R.color.C1) }
    private val mC2 by lazy { ContextCompat.getColor(this@PersonalInfoActivity, R.color.C2) }

    override fun layout() = R.layout.activity_personal_info

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.personal_info)

        tv_phone_number.text = mUserInfo?.account

        //TODO 根据kycStatus设置不同文案、背景以及按钮点击事件
        btn_kyc.apply {
            when (mUserInfo?.kycStatus) {
                Constant.VERIFY_PASSED -> {
                    setOnClickListener { }
                    setText(R.string.kyc_identified)
                    setTextColor(mC1)
                    setBackgroundResource(R.drawable.round_rect_solid_light_blue_bg)
                }
                Constant.IN_VERIFYING -> {
                    //TODO 审核中
                    toast("审核中")
                }
                else -> {
                    setOnClickListener { ARouter.getInstance().build(Router.KYC_ACTIVITY).navigation() }
                    setText(R.string.kyc_identification)
                    setTextColor(mC2)
                    setBackgroundResource(R.drawable.round_rect_solid_dark_blue_bg)
                }
            }
        }
    }

}
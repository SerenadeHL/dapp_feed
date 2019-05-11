package com.axonomy.dapp_feed.ui.activity

import android.text.SpannableString
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router

/**
 * 划转成功页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-08 20:02:58
 */
@Route(path = Router.RECHARGE_SUCCESS_ACTIVITY)
class RechargeSuccessActivity : SuccessParentActivity() {

    override fun getTitleResId() = R.string.recharge_success

    override fun getDescription(): SpannableString {
        return SpannableString(getString(R.string.recharge_success_description))
    }
}
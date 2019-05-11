package com.axonomy.dapp_feed.ui.activity

import android.text.SpannableString
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router

/**
 * 充值成功页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-08 20:02:58
 */
@Route(path = Router.TRANSFER_SUCCESS_ACTIVITY)
class TransferSuccessActivity : SuccessParentActivity() {

    override fun getTitleResId() = R.string.transfer_success

    override fun getDescription(): SpannableString {
        return SpannableString(getString(R.string.transfer_success_description))
    }
}
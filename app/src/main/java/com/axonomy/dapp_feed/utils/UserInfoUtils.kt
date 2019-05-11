package com.axonomy.dapp_feed.utils

import android.text.TextUtils
import com.axonomy.dapp_feed.constant.Constant
import com.axonomy.dapp_feed.bean.wallet.UserInfoBean
import me.serenadehl.base.extensions.fromJson
import me.serenadehl.base.utils.sharedpre.SPUtil

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 16:30:12
 */
object UserInfoUtils {
    fun getUserInfo(): UserInfoBean? {
        val json = SPUtil.getString(Constant.USER_INFO)
        return if (TextUtils.isEmpty(json)) {
            null
        } else {
            json.fromJson(UserInfoBean::class.java)
        }
    }
}
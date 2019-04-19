package com.dong.dapp.utils

import android.text.TextUtils
import com.dong.dapp.Constant
import com.dong.dapp.bean.wallet.UserInfoBean
import com.dong.dapp.extensions.fromJson
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
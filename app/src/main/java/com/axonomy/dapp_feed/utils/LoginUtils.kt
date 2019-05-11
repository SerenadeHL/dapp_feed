package com.axonomy.dapp_feed.utils

import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.constant.Constant
import com.axonomy.dapp_feed.bean.wallet.UserInfoBean
import com.axonomy.dapp_feed.constant.Router
import me.serenadehl.base.extensions.toJson
import me.serenadehl.base.utils.sharedpre.SPUtil


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-12 14:20:31
 */
object LoginUtils {

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        return !TextUtils.isEmpty(SPUtil.getString(Constant.TOKEN))
    }

    /**
     * 打开登录页
     */
    fun goLogin() {
        ARouter.getInstance().build(Router.LOGIN_ACTIVITY).navigation()
    }

    /**
     * 保存登录标识
     */
    fun saveLoginTag(token: String) {
        SPUtil.putString(Constant.TOKEN, token)
    }

    /**
     * 保存用户信息
     */
    fun saveUserInfo(userInfoBean: UserInfoBean?) {
        SPUtil.putString(Constant.USER_INFO, userInfoBean?.toJson() ?: "")
    }

    /**
     * 移除登录标识
     */
    fun removeLoginTag() {
        SPUtil.putString(Constant.TOKEN, "")
        SPUtil.putString(Constant.USER_INFO, "")
    }
}
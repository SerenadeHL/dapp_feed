package com.axonomy.dapp_feed.extensions

import com.axonomy.dapp_feed.constant.Constant
import com.axonomy.dapp_feed.bean.wallet.UserInfoBean
import me.serenadehl.base.extensions.toJson
import me.serenadehl.base.utils.sharedpre.SPUtil

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-10 17:37:54
 */
inline fun UserInfoBean.save() {
    SPUtil.putString(Constant.USER_INFO, this.toJson())
}

package com.dong.dapp.utils

import android.app.Activity
import com.dong.dapp.bean.update.ResultUpdateInfoBean
import com.dong.dapp.widget.UpdateDialog

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 10:18:43
 */
object UpdateUtils {
    /**
     * 检查更新
     */
    fun checkUpdate(activity: Activity, data: ResultUpdateInfoBean?) {
        if (data?.updateType == 2 || data == null) return//不显示
        UpdateDialog(activity, data).show()
    }
}
package com.axonomy.dapp_feed.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import com.axonomy.dapp_feed.R

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-11 11:12:26
 */
object DialogUtils {
    fun show(activity: Activity, titleResId: Int, listener: DialogInterface.OnClickListener) {
        show(activity, titleResId, null, listener)
    }

    fun show(activity: Activity, titleResId: Int, msg: String?, listener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(activity)
            .setTitle(titleResId)
            .setMessage(msg)
            .setPositiveButton(R.string.sure, listener)
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}
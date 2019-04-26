package com.dong.dapp.utils

import android.content.Context
import me.serenadehl.base.utils.app.AppManager

import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-16 11:30:12
 */
object AssetsUtils {

    fun getAssets(fileName: String): String {
        var jsStr = ""
        try {
            val inputStream = AppManager.instance.currentActivity.applicationContext.assets.open(fileName)
            val buff = ByteArray(1024)
            val fromFile = ByteArrayOutputStream()
            do {
                val numRead = inputStream.read(buff)
                if (numRead <= 0) {
                    break
                }
                fromFile.write(buff, 0, numRead)
            } while (true)
            jsStr = fromFile.toString()
            inputStream.close()
            fromFile.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return jsStr
    }
}

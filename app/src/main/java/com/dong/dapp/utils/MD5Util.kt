package com.dong.dapp.utils

import java.io.File
import java.io.FileInputStream
import java.math.BigInteger
import java.security.MessageDigest

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-01-21 14:34:06
 */
object MD5Util {
    fun getMD5(file: File): String? {
        if (!file.isFile) {
            return null
        }

        val digest: MessageDigest
        try {
            digest = MessageDigest.getInstance("MD5")
            val fis = FileInputStream(file)
            val buffer = ByteArray(1024)
            var len = fis.read(buffer, 0, 1024)
            while (len != -1) {
                digest.update(buffer, 0, len)
                len = fis.read(buffer, 0, 1024)
            }
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val bigInt = BigInteger(1, digest?.digest())
        return bigInt.toString(16)
    }
}

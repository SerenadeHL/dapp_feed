package com.dong.dapp.utils

import android.util.Base64
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 15:22:51
 */
object RSAUtils {
//    private external fun getPublicKey():String

    private const val PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCl0+yOleAMVbO0zW2WdC0TX6q7\n" +
            "g8kvCZxJRA4PuuqfqondTiUJTg6RUFSKc9vWbufdczprV+N8k2R4t8hq5oRfUKhJ\n" +
            "1XirsJcuKQsGg2P2/hG7g4mCZbyJrDYGuEaY8E1igQtidcIlOeaatygzhkadr/jT\n" +
            "EtonpJAn7oFCYOqzIQIDAQAB\n" +
            "-----END PUBLIC KEY-----"

    private fun getPublicKey(): String {
        return PUBLIC_KEY.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
    }

    fun encrypt(content: String): String {
        var encoded = ""
        var encrypted: ByteArray? = null
        try {
            val publicBytes = Base64.decode(getPublicKey(), Base64.DEFAULT)
            val keySpec = X509EncodedKeySpec(publicBytes)
            val keyFactory = KeyFactory.getInstance("RSA")
            val pubKey = keyFactory.generatePublic(keySpec)
            val cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey)
            encrypted = cipher.doFinal(content.toByteArray())
            encoded = Base64.encodeToString(encrypted, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encoded
    }
}
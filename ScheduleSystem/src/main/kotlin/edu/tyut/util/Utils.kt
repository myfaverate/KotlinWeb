package edu.tyut.util

import java.security.MessageDigest

object Utils {
    /**
     * ```kotlin
     *    println(toMD5("zsh123.."))
     *    println(toMD5("lisi123.."))
     * ```
     * MD5字符串加密
     */
    fun toMD5(content: String): String {
        val messageDigest: MessageDigest = MessageDigest.getInstance("MD5")
        val bytes: ByteArray = messageDigest.digest(content.toByteArray(charset = Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
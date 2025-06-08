package edu.tyut.spring_boot_ssm.extension

import java.security.MessageDigest

internal fun String.md5(): String {
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    val bytes: ByteArray = md.digest(this.toByteArray())
    return bytes.joinToString(separator = "") { byte: Byte ->
        "%02x".format(args = arrayOf(byte))
    }
}
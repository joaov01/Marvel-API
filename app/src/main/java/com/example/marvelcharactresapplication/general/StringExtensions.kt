package com.example.marvelcharactresapplication.general

import java.security.MessageDigest

fun String.toMd5(): String {
    try {
        val md5Encoder = MessageDigest.getInstance("MD5")
        val digest = md5Encoder.digest(this.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    } catch (e: Exception) {
        e.message
    }
    return ""
}
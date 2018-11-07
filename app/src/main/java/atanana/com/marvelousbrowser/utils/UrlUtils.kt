package atanana.com.marvelousbrowser.utils

import atanana.com.marvelousbrowser.BuildConfig
import okhttp3.HttpUrl
import java.math.BigInteger
import java.security.MessageDigest

fun digestUrl(url: String): String = digestUrl(HttpUrl.parse(url)!!)

fun digestUrl(url: HttpUrl): String {
    val timestamp = System.currentTimeMillis().toString()
    return url
        .newBuilder()
        .addQueryParameter("apikey", BuildConfig.PUBLIC_API_KEY)
        .addQueryParameter("ts", timestamp)
        .addQueryParameter("hash", buildHash(timestamp))
        .build()
        .toString()
}

private fun buildHash(timestamp: String): String =
    md5(timestamp + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY)

private fun md5(string: String): String {
    val messageDigest = MessageDigest.getInstance("MD5")
    val digest = messageDigest.digest(string.toByteArray())
    return BigInteger(1, digest).toString(16)
}
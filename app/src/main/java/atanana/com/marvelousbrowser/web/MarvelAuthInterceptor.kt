package atanana.com.marvelousbrowser.web

import atanana.com.marvelousbrowser.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_API_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", buildHash(timestamp))
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

    private fun buildHash(timestamp: String): String =
        md5(timestamp + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY)

    private fun md5(string: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        val digest = messageDigest.digest(string.toByteArray())
        return BigInteger(1, digest).toString(16)
    }
}
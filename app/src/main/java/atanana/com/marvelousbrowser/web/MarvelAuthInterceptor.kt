package atanana.com.marvelousbrowser.web

import atanana.com.marvelousbrowser.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MarvelAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
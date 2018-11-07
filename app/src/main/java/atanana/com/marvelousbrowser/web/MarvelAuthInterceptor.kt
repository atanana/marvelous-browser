package atanana.com.marvelousbrowser.web

import atanana.com.marvelousbrowser.utils.digestUrl
import okhttp3.Interceptor
import okhttp3.Response

class MarvelAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = digestUrl(chain.request().url())
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
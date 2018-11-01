package atanana.com.marvelousbrowser

import atanana.com.marvelousbrowser.web.MarvelAuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {
    single { buildRetrofit() }
}

private fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/v1/public")
        .client(buildOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private const val TIMEOUT = 30L

private fun buildOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(MarvelAuthInterceptor())
        .build()
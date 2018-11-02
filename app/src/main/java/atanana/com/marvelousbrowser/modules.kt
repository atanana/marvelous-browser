package atanana.com.marvelousbrowser

import atanana.com.marvelousbrowser.screens.characters.CharactersPresenter
import atanana.com.marvelousbrowser.web.MarvelAuthInterceptor
import atanana.com.marvelousbrowser.web.MarvelService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {
    single { buildRetrofit() }
    single { get<Retrofit>().create(MarvelService::class.java) }
    single { buildMoshi() }

    single { CharactersPresenter(get(), get()) }
}

private fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/")
        .client(buildOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
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

private fun buildMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
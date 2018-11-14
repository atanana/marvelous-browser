package atanana.com.marvelousbrowser

import android.arch.persistence.room.Room
import android.content.Context
import atanana.com.marvelousbrowser.data.room.MarvelousDatabase
import atanana.com.marvelousbrowser.screens.characters.CharactersDataSource
import atanana.com.marvelousbrowser.screens.characters.CharactersDataSourceFactory
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
    single { buildDatabase(get()) }

    single { CharactersPresenter(get()) }
    single { CharactersDataSource(get(), get()) }
    single { CharactersDataSourceFactory(get()) }
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

private fun buildDatabase(context: Context): MarvelousDatabase =
    Room.databaseBuilder(context, MarvelousDatabase::class.java, "marvelous-db").build()
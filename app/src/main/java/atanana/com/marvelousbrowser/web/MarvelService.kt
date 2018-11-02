package atanana.com.marvelousbrowser.web

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("/characters")
    fun characters(): Deferred<Response<Any>>
}
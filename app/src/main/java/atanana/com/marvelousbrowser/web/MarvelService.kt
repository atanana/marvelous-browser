package atanana.com.marvelousbrowser.web

import atanana.com.marvelousbrowser.data.MarvelResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MarvelService {
    @GET("/v1/public/characters")
    fun characters(): Deferred<MarvelResponse>
}
package atanana.com.marvelousbrowser.web

import atanana.com.marvelousbrowser.data.MarvelResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("/v1/public/characters")
    fun characters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Deferred<MarvelResponse>

    companion object {
        const val MAX_LIMIT = 100
    }
}
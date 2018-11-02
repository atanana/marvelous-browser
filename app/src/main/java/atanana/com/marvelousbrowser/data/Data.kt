package atanana.com.marvelousbrowser.data

import com.squareup.moshi.Json
import org.json.JSONArray

data class Data(
    @Json(name = "count")
    val count: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val results: List<Any>,
    @Json(name = "total")
    val total: Int
) {
    val resultsString: String
        get() = JSONArray(results).toString()
}
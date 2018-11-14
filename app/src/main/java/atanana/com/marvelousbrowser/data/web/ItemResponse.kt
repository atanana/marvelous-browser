package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class ItemResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "type")
    val type: String = ""
)
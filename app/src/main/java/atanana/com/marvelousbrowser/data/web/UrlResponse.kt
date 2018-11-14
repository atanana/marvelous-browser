package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class UrlResponse(
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)
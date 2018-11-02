package atanana.com.marvelousbrowser.data

import com.squareup.moshi.Json

data class Url(
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)
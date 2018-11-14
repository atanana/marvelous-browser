package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class MarvelResponse(
    @Json(name = "attributionHTML")
    val attributionHTML: String,
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "code")
    val code: Int,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val `data`: DataResponse,
    @Json(name = "etag")
    val etag: String,
    @Json(name = "status")
    val status: String
)
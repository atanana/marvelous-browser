package atanana.com.marvelousbrowser.data

import com.squareup.moshi.Json

data class Item(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "type")
    val type: String
)
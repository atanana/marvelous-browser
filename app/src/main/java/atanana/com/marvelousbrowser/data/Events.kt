package atanana.com.marvelousbrowser.data

import com.squareup.moshi.Json

data class Events(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<ItemX>,
    @Json(name = "returned")
    val returned: Int
)
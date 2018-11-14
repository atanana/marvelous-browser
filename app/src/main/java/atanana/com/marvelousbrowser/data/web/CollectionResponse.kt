package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class CollectionResponse(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<ItemResponse>,
    @Json(name = "returned")
    val returned: Int
)
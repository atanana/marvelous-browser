package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class ThumbnailResponse(
    @Json(name = "extension")
    val extension: String,
    @Json(name = "path")
    val path: String
) {
    val fullPath: String
        get() = "$path.$extension"
}
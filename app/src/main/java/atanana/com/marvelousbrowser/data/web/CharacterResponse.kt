package atanana.com.marvelousbrowser.data.web

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "comics")
    val comics: CollectionResponse,
    @Json(name = "description")
    val description: String,
    @Json(name = "events")
    val events: CollectionResponse,
    @Json(name = "id")
    val id: Int,
    @Json(name = "modified")
    val modified: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "series")
    val series: CollectionResponse,
    @Json(name = "stories")
    val stories: CollectionResponse,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailResponse,
    @Json(name = "urls")
    val urls: List<UrlResponse>
)
package atanana.com.marvelousbrowser.data

import com.squareup.moshi.Json

data class Character(
    @Json(name = "comics")
    val comics: MarvelCollection,
    @Json(name = "description")
    val description: String,
    @Json(name = "events")
    val events: MarvelCollection,
    @Json(name = "id")
    val id: Int,
    @Json(name = "modified")
    val modified: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "series")
    val series: MarvelCollection,
    @Json(name = "stories")
    val stories: MarvelCollection,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,
    @Json(name = "urls")
    val urls: List<Url>
)
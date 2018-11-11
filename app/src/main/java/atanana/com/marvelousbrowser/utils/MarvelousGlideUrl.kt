package atanana.com.marvelousbrowser.utils

import com.bumptech.glide.load.model.GlideUrl

class MarvelousGlideUrl(private val url: String) : GlideUrl(digestUrl(url)) {
    override fun toString(): String = cacheKey

    override fun getCacheKey(): String = url
}
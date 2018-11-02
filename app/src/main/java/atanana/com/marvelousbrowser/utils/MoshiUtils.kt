package atanana.com.marvelousbrowser.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

inline fun <reified T> Moshi.parseResult(json: String): List<T> {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    return adapter<List<T>>(type).fromJson(json) ?: emptyList()
}
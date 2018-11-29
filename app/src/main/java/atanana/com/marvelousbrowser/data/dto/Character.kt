package atanana.com.marvelousbrowser.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val thumbnailUrl: String
) : Parcelable
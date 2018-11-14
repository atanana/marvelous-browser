package atanana.com.marvelousbrowser.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val modified: String,
    val resourceURI: String,
    val description: String
)
package atanana.com.marvelousbrowser.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "modified")
    val modified: String,
    @ColumnInfo(name = "resourceURI")
    val resourceURI: String,
    @ColumnInfo(name = "description")
    val description: String
)
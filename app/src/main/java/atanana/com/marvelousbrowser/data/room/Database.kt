package atanana.com.marvelousbrowser.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelousDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
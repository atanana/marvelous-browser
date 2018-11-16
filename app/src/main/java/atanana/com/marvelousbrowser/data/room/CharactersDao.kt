package atanana.com.marvelousbrowser.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CharactersDao {
    @Query("select * from characterEntity limit :limit offset :offset")
    fun query(limit: Int, offset: Int): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(characters: List<CharacterEntity>)
}
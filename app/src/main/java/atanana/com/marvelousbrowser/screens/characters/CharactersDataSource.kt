package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PositionalDataSource
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.data.dto.toCharacters
import atanana.com.marvelousbrowser.data.dto.toCharacters2
import atanana.com.marvelousbrowser.data.dto.toEntities
import atanana.com.marvelousbrowser.data.room.MarvelousDatabase
import atanana.com.marvelousbrowser.data.web.CharacterResponse
import atanana.com.marvelousbrowser.utils.parseResult
import atanana.com.marvelousbrowser.web.MarvelService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.min

class CharactersDataSource(
    private val marvelService: MarvelService,
    private val moshi: Moshi,
    database: MarvelousDatabase
) : PositionalDataSource<Character>() {
    private val charactersDao = database.charactersDao()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        GlobalScope.launch(Dispatchers.IO) {
            val limit = min(params.loadSize, MarvelService.MAX_LIMIT)
            val characters = loadCharacters(params.startPosition, limit)
            callback.onResult(characters)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        GlobalScope.launch(Dispatchers.IO) {
            val limit = min(params.requestedLoadSize, MarvelService.MAX_LIMIT)
            val startPosition = params.requestedStartPosition
            val characters = loadCharacters(startPosition, limit)
            callback.onResult(characters, startPosition)
        }
    }

    private suspend fun loadCharacters(offset: Int, limit: Int): List<Character> {
        val characters = charactersDao.query(limit, offset)
        return if (characters.isNotEmpty()) {
            characters.toCharacters2()
        } else {
            loadCharactersFromWeb(offset, limit)
        }
    }

    private suspend fun loadCharactersFromWeb(offset: Int, limit: Int): List<Character> {
        val response = marvelService.characters(offset = offset, limit = limit).await()
        val charactersResponse = moshi.parseResult<CharacterResponse>(response.data.resultsString)
        val characters = charactersResponse.toCharacters()
        storeCharacters(characters)
        return characters
    }

    private fun storeCharacters(characters: List<Character>) {
        GlobalScope.launch(Dispatchers.IO) {
            charactersDao.insertOrUpdate(characters.toEntities())
        }
    }
}
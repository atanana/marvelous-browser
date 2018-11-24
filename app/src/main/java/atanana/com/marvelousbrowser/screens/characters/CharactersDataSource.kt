package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PositionalDataSource
import atanana.com.marvelousbrowser.data.MarvelousPreferences
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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlin.math.min

private typealias LoadResult = Pair<List<Character>, Int>

class CharactersDataSource(
    private val marvelService: MarvelService,
    private val moshi: Moshi,
    database: MarvelousDatabase,
    private val preferences: MarvelousPreferences
) : PositionalDataSource<Character>() {
    private val charactersDao = database.charactersDao()
    private val loadingStateChannel: Channel<Boolean> = Channel(CONFLATED)

    val loadingState: ReceiveChannel<Boolean> = loadingStateChannel

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        GlobalScope.launch(Dispatchers.IO) {
            val limit = min(params.loadSize, MarvelService.MAX_LIMIT)
            val loadResult = loadCharacters(params.startPosition, limit)
            callback.onResult(loadResult.first)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        GlobalScope.launch(Dispatchers.IO) {
            val limit = min(params.requestedLoadSize, MarvelService.MAX_LIMIT)
            val startPosition = params.requestedStartPosition
            notifyLoading {
                val loadResult = loadCharacters(startPosition, limit)
                callback.onResult(loadResult.first, startPosition, loadResult.second)
            }
        }
    }

    private suspend inline fun notifyLoading(block: () -> Unit) {
        loadingStateChannel.send(true)
        block()
        loadingStateChannel.send(false)
    }

    private suspend fun loadCharacters(offset: Int, limit: Int): LoadResult {
        val dbResult = charactersDao.query(limit, offset)
        return if (dbResult.isNotEmpty()) {
            Pair(dbResult.toCharacters2(), preferences.totalCharacters)
        } else {
            loadCharactersFromWeb(offset, limit)
        }
    }

    private suspend fun loadCharactersFromWeb(offset: Int, limit: Int): LoadResult {
        val response = marvelService.characters(offset = offset, limit = limit).await()
        val charactersResponse = moshi.parseResult<CharacterResponse>(response.data.resultsString)
        val characters = charactersResponse.toCharacters()
        val result = Pair(characters, response.data.total)
        storeResult(result)
        return result
    }

    private fun storeResult(loadResult: LoadResult) {
        GlobalScope.launch(Dispatchers.IO) {
            charactersDao.insertOrUpdate(loadResult.first.toEntities())
            preferences.totalCharacters = loadResult.second
        }
    }
}
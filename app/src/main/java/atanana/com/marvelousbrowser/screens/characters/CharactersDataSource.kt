package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PositionalDataSource
import atanana.com.marvelousbrowser.data.Character
import atanana.com.marvelousbrowser.utils.parseResult
import atanana.com.marvelousbrowser.web.MarvelService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.min

class CharactersDataSource(
    private val marvelService: MarvelService,
    private val moshi: Moshi
) : PositionalDataSource<Character>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        GlobalScope.launch {
            val limit = min(params.loadSize, MarvelService.MAX_LIMIT)
            val response = marvelService.characters(offset = params.startPosition, limit = limit).await()
            val characters = moshi.parseResult<Character>(response.data.resultsString)
            callback.onResult(characters)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        GlobalScope.launch {
            val limit = min(params.requestedLoadSize, MarvelService.MAX_LIMIT)
            val startPosition = params.requestedStartPosition
            val response = marvelService.characters(offset = startPosition, limit = limit).await()
            val characters = moshi.parseResult<Character>(response.data.resultsString)
            callback.onResult(characters, startPosition, response.data.total)
        }
    }
}
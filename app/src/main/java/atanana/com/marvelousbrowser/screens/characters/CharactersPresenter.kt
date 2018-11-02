package atanana.com.marvelousbrowser.screens.characters

import atanana.com.marvelousbrowser.data.Character
import atanana.com.marvelousbrowser.utils.parseResult
import atanana.com.marvelousbrowser.web.MarvelService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersPresenter(
    private val marvelService: MarvelService,
    private val moshi: Moshi
) {
    var charactersView: CharactersView? = null
        set(value) {
            field = value
            value?.setCharacters(characters)
        }

    private var characters: List<Character> = emptyList()
        set(value) {
            field = value
            charactersView?.setCharacters(value)
        }

    init {
        GlobalScope.launch(Dispatchers.Main) {
            val response = marvelService.characters().await()
            val newCharacters = moshi.parseResult<Character>(response.data.resultsString)
            characters = newCharacters
        }
    }
}
package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PagedList
import atanana.com.marvelousbrowser.data.web.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class CharactersPresenter(private val charactersDataSource: CharactersDataSource) {
    private val pageConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    private val pagedList by lazy {
        PagedList.Builder<Int, CharacterResponse>(charactersDataSource, pageConfig)
            .setNotifyExecutor {
                GlobalScope.launch(Dispatchers.Main) { it.run() }
            }
            .setFetchExecutor(Executors.newFixedThreadPool(2))
            .build()
    }

    var charactersView: CharactersView? = null
        set(value) {
            field = value
            value?.setCharacters(pagedList)
        }
}
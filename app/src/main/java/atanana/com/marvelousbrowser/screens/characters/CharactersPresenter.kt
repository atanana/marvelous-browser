package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PagedList
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.utils.MarvelousExecutors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersPresenter(private val charactersDataSource: CharactersDataSource) {
    private val pageConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    private val pagedList by lazy {
        PagedList.Builder<Int, Character>(charactersDataSource, pageConfig)
            .setNotifyExecutor(MarvelousExecutors.ui)
            .setFetchExecutor(MarvelousExecutors.io)
            .build()
    }

    var charactersView: CharactersView? = null
        set(value) {
            field = value
            value?.setCharacters(pagedList)
        }

    init {
        GlobalScope.launch(Dispatchers.Main) {
            for (isLoading in charactersDataSource.loadingState) {
                charactersView?.setLoading(isLoading)
            }
        }
    }
}
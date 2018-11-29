package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PagedList
import android.widget.ImageView
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.screens.MarvelousRouter
import atanana.com.marvelousbrowser.utils.MarvelousExecutors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharactersPresenter(private val charactersDataSource: CharactersDataSource, private val router: MarvelousRouter) {
    private lateinit var scopeJob: Job

    private val pageConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
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
            if (value != null) {
                value.setCharacters(pagedList)
                scopeJob = Job()
                watchLoading()
            } else {
                scopeJob.cancel()
            }
        }


    private fun watchLoading() {
        GlobalScope.launch(Dispatchers.Main + scopeJob) {
            for (isLoading in charactersDataSource.loadingState) {
                charactersView?.setLoading(isLoading)
            }
        }
    }

    fun onCharacterClick(character: Character, imageView: ImageView) {
        router.openCharacterDetails(character, imageView)
    }
}
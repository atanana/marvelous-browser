package atanana.com.marvelousbrowser.screens.characters

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import atanana.com.marvelousbrowser.data.Character

class CharactersPresenter(private val dataSourceFactory: CharactersDataSourceFactory) {
    private val pageConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    private val charactersData = LivePagedListBuilder<Int, Character>(dataSourceFactory, pageConfig).build()

    var charactersView: CharactersView? = null
        set(value) {
            field = value
            if (value != null) {
                observeCharactersData(value)
            }
        }

    private fun observeCharactersData(value: CharactersView) {
        charactersData.observe(value, Observer { list ->
            if (list != null) {
                value.setCharacters(list)
            }
        })
    }
}
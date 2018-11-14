package atanana.com.marvelousbrowser.screens.characters

import android.arch.lifecycle.LifecycleOwner
import android.arch.paging.PagedList
import atanana.com.marvelousbrowser.data.web.CharacterResponse

interface CharactersView : LifecycleOwner {
    fun setCharacters(characters: PagedList<CharacterResponse>)
}
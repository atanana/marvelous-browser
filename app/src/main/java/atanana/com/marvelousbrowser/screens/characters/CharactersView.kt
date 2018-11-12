package atanana.com.marvelousbrowser.screens.characters

import android.arch.lifecycle.LifecycleOwner
import android.arch.paging.PagedList
import atanana.com.marvelousbrowser.data.Character

interface CharactersView : LifecycleOwner {
    fun setCharacters(characters: PagedList<Character>)
}
package atanana.com.marvelousbrowser.screens.characters

import atanana.com.marvelousbrowser.data.Character

interface CharactersView {
    fun setCharacters(characters: List<Character>)
}
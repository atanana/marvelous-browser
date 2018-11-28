package atanana.com.marvelousbrowser.screens

import android.support.v4.app.FragmentManager
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.screens.characterdetails.CharacterDetailsFragment

class MarvelousRouter(private val fragmentManager: FragmentManager) {
    fun openCharacterDetails(characterId: Int) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, CharacterDetailsFragment.newInstance())
            .addToBackStack(CharacterDetailsFragment::class.simpleName)
            .commit()
    }
}
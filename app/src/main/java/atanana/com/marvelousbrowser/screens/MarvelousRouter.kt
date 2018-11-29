package atanana.com.marvelousbrowser.screens

import android.support.v4.app.FragmentManager
import android.transition.Fade
import android.transition.TransitionInflater
import android.widget.ImageView
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.screens.characterdetails.CharacterDetailsFragment

class MarvelousRouter(private val fragmentManager: FragmentManager, private val inflater: TransitionInflater) {
    fun openCharacterDetails(character: Character, imageView: ImageView) {
        val previousFragment = fragmentManager.findFragmentById(R.id.container)
        val nextFragment = CharacterDetailsFragment.newInstance(character)

        previousFragment?.exitTransition = Fade().apply { duration = 300 }

//        nextFragment.sharedElementEnterTransition = TransitionSet().apply {
//            addTransition(inflater.inflateTransition(android.R.transition.move))
//            duration = 1000
//            startDelay = 300
//        }
        nextFragment.postponeEnterTransition()
//        nextFragment.enterTransition = Fade().apply {
//            startDelay = 1300
//            duration = 300
//        }

        nextFragment.sharedElementEnterTransition = inflater.inflateTransition(android.R.transition.move)

        fragmentManager.beginTransaction()
            .addSharedElement(imageView, imageView.transitionName)
            .replace(R.id.container, nextFragment)
            .addToBackStack(CharacterDetailsFragment::class.simpleName)
            .commitAllowingStateLoss()
    }
}
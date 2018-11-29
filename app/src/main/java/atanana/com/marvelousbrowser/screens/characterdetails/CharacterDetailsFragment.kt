package atanana.com.marvelousbrowser.screens.characterdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.SCOPE_FRAGMENT
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.utils.load
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class CharacterDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_character_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindScope(getOrCreateScope(SCOPE_FRAGMENT))

        val character = arguments?.getParcelable<Character>(KEY_CHARACTER)
        if (character != null) {
            avatar.transitionName = character.id.toString()
            avatar.load(character.thumbnailUrl) {
                startPostponedEnterTransition()
            }
        }
    }

    companion object {
        private const val KEY_CHARACTER = "character"

        fun newInstance(character: Character) = CharacterDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_CHARACTER, character)
            }
        }
    }
}
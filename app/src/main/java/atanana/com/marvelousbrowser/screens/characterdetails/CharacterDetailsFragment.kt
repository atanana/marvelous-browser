package atanana.com.marvelousbrowser.screens.characterdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.SCOPE_FRAGMENT
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class CharacterDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_character_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindScope(getOrCreateScope(SCOPE_FRAGMENT))
    }

    companion object {
        fun newInstance() = CharacterDetailsFragment()
    }
}
package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.SCOPE_FRAGMENT
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.utils.setVisible
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class CharactersFragment : Fragment(), CharactersView {
    private val charactersPresenter: CharactersPresenter by inject()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindScope(getOrCreateScope(SCOPE_FRAGMENT))

        list.layoutManager = LinearLayoutManager(context)
        charactersAdapter = CharactersAdapter()
        list.adapter = charactersAdapter
    }

    override fun setCharacters(characters: PagedList<Character>) {
        charactersAdapter.submitList(characters)
    }

    override fun setLoading(isLoading: Boolean) {
        list.setVisible(!isLoading)
        progressbar.setVisible(isLoading)
    }

    override fun onResume() {
        super.onResume()
        charactersPresenter.charactersView = this
    }

    override fun onPause() {
        super.onPause()
        charactersPresenter.charactersView = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}

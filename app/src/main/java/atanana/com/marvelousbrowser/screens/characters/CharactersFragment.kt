package atanana.com.marvelousbrowser.screens.characters

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.web.CharacterResponse
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.ext.android.inject

class CharactersFragment : Fragment(), CharactersView {
    private val charactersPresenter: CharactersPresenter by inject()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.layoutManager = LinearLayoutManager(context)
        charactersAdapter = CharactersAdapter()
        list.adapter = charactersAdapter

        charactersPresenter.charactersView = this
    }

    override fun setCharacters(characters: PagedList<CharacterResponse>) {
        charactersAdapter.submitList(characters)
        list.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}

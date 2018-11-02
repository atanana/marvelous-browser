package atanana.com.marvelousbrowser.screens.characters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.Character
import org.koin.android.ext.android.inject

class CharactersFragment : Fragment(), CharactersView {
    private val charactersPresenter: CharactersPresenter by inject()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

                charactersAdapter = CharactersAdapter()
                adapter = charactersAdapter
            }
            charactersPresenter.charactersView = this
        }
        return view
    }

    override fun setCharacters(characters: List<Character>) {
        charactersAdapter.characters = characters
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}

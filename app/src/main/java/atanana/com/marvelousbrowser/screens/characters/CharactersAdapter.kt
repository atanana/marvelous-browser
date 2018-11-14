package atanana.com.marvelousbrowser.screens.characters


import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.web.CharacterResponse
import atanana.com.marvelousbrowser.utils.load
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter : PagedListAdapter<CharacterResponse, CharactersAdapter.ViewHolder>(DIFF_CALLBACK) {
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as CharacterResponse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.bind(character)

            with(holder.view) {
                tag = character
                setOnClickListener(mOnClickListener)
            }
        } else {
            holder.clear()
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.character_image
        private val name = view.character_name

        fun bind(character: CharacterResponse) {
            name.text = character.name
            loadImage(character)
        }

        private fun loadImage(character: CharacterResponse) {
            val url = character.thumbnail.fullPath
            image.load(url)
        }

        fun clear() {
            name.text = ""
            image.setImageResource(R.drawable.placeholder)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharacterResponse>() {
            override fun areItemsTheSame(p0: CharacterResponse, p1: CharacterResponse): Boolean = p0.id == p1.id

            override fun areContentsTheSame(p0: CharacterResponse, p1: CharacterResponse): Boolean = p0 == p1
        }
    }
}
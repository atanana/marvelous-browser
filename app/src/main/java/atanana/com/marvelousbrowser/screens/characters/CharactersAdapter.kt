package atanana.com.marvelousbrowser.screens.characters


import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.dto.Character
import atanana.com.marvelousbrowser.utils.load
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter(private val onItemClick: (Character, ImageView) -> Unit) :
    PagedListAdapter<Character, CharactersAdapter.ViewHolder>(DIFF_CALLBACK) {
    private val onClickListener = View.OnClickListener {
        val item = it.tag as? Character
        if (item != null) {
            onItemClick(item, it.character_image)
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
                setOnClickListener(onClickListener)
            }
        } else {
            holder.clear()
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.character_image
        private val name = view.character_name

        fun bind(character: Character) {
            name.text = character.name
            image.load(character.thumbnailUrl)
            image.transitionName = character.id.toString()
        }

        fun clear() {
            name.text = ""
            image.setImageResource(R.drawable.placeholder)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(p0: Character, p1: Character): Boolean = p0.id == p1.id

            override fun areContentsTheSame(p0: Character, p1: Character): Boolean = p0 == p1
        }
    }
}
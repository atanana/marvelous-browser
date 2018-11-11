package atanana.com.marvelousbrowser.screens.characters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.marvelousbrowser.R
import atanana.com.marvelousbrowser.data.Character
import atanana.com.marvelousbrowser.utils.GlideApp
import atanana.com.marvelousbrowser.utils.MarvelousGlideUrl
import atanana.com.marvelousbrowser.utils.digestUrl
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.signature.ObjectKey
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    var characters: List<Character> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        with(holder.view) {
            tag = character
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.character_image
        private val name = view.character_name

        fun bind(character: Character) {
            name.text = character.name
            loadImage(character)
        }

        private fun loadImage(character: Character) {
            val url = character.thumbnail.fullPath
            GlideApp.with(view)
                .load(MarvelousGlideUrl(url))
                .placeholder(R.drawable.ic_launcher_background)
                .transition(withCrossFade())
                .signature(ObjectKey(url))
                .into(image)
        }
    }
}

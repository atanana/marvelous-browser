package atanana.com.marvelousbrowser.utils

import android.widget.ImageView
import atanana.com.marvelousbrowser.R
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.signature.ObjectKey

fun ImageView.load(url: String) {
    GlideApp.with(this)
        .load(MarvelousGlideUrl(url))
        .placeholder(R.drawable.placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .signature(ObjectKey(url))
        .into(this)
}

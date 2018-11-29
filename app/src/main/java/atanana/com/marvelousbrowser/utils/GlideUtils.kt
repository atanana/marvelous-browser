package atanana.com.marvelousbrowser.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import atanana.com.marvelousbrowser.R
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey

fun ImageView.load(url: String, onReady: (() -> Unit)? = null) {
    GlideApp.with(this)
        .load(MarvelousGlideUrl(url))
        .placeholder(R.drawable.placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onReady?.invoke()
                return false
            }
        })
        .signature(ObjectKey(url))
        .into(this)
}

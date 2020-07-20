package br.com.bernardoroll.catho.adapter

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.mikhaellopez.circularimageview.CircularImageView

@BindingAdapter("setCircularImageDrawable")
fun CircularImageView.setCircularImageDrawable(drawable: Drawable?) {
    drawable?.let {
        this.setImageDrawable(it)
    }
}

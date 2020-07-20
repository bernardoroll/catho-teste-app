package br.com.bernardoroll.catho.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.databinding.BindingAdapter
import com.mikhaellopez.circularimageview.CircularImageView

@BindingAdapter("setCircularImageDrawable")
fun CircularImageView.setCircularImageDrawable(drawable: Drawable?) {
    drawable?.let {
        this.setImageDrawable(it)
    }
}

@BindingAdapter("present")
fun View.setVisibility(present: Boolean? = false) {
    this.visibility = if (present == true) View.VISIBLE else View.GONE
}

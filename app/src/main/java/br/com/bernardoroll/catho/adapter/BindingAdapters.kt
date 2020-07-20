package br.com.bernardoroll.catho.adapter

import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
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

@BindingAdapter("srcTint")
fun AppCompatImageView.setSrcTint(color: Int) {
    this.imageTintList = ColorStateList.valueOf(color)
}

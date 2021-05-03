package com.dahyun.base.ext

import android.graphics.drawable.Drawable
import android.widget.ImageButton
import androidx.databinding.BindingAdapter

@BindingAdapter("imageButtonDrawable")
fun setImageDrawable(view: ImageButton, drawable: Drawable) {
    view.setImageDrawable(drawable)
}

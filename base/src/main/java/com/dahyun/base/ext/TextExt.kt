package com.dahyun.base.ext

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("setHtmlText")
fun TextView.setHtmlText(htmlString: String?) {
    if (htmlString.isNullOrEmpty()) {
        return
    }
    text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
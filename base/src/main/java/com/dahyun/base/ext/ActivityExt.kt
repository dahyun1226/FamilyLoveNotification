package com.dahyun.base.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

fun Activity.showSoftKeyBoard() {
    val view = currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

fun FragmentActivity.showSoftKeyBoard() {
    val view = currentFocus
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

fun Activity.hideSoftKeyBoard() {
    val view = currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
    view?.clearFocus()
}

fun FragmentActivity.hideSoftKeyBoard() {
    val view = currentFocus
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    view?.clearFocus()
}
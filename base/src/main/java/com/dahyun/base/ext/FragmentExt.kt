package com.dahyun.base.ext

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.checkSelfPermissionCompat(permission: String) =
    ContextCompat.checkSelfPermission(requireContext(), permission)

fun Fragment.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)

fun Fragment.toast(message: CharSequence?) {
    if (message.isNullOrEmpty()) return
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(@StringRes messageId: Int) {
    Toast.makeText(requireContext(), messageId, Toast.LENGTH_SHORT).show()
}

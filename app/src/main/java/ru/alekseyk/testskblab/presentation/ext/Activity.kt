package ru.alekseyk.testskblab.presentation.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(@StringRes textResource: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textResource, length).show()
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Activity.hideKeyboard() {
    val inputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.currentFocus?.let { focusedView ->
        inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
    }
}

fun <T> Activity.argSerializable(key: String) = lazy { intent!!.getSerializableExtra(key)!! as T }
fun <T> Activity.argSerializableNullable(key: String) =
    lazy { intent?.getSerializableExtra(key) as T }
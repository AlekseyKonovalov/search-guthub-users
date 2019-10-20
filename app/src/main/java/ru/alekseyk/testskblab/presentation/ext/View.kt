package ru.alekseyk.testskblab.presentation.ext

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.Observable

var TextView.diffedValue: String
    get() = text.toString()
    set(value) {
        if (text.toString() == value) return
        text = value
    }

var CheckBox.diffedValue: Boolean
    get() = isChecked
    set(value) {
        if (isChecked == value) return
        isChecked = value
    }

var SwitchCompat.diffedValue: Boolean
    get() = isChecked
    set(value) {
        if (isChecked == value) return
        isChecked = value
    }

var TextInputLayout.diffedError: String
    get() = error.toString()
    set(value) {
        if (error.toString() == value) return
        error = value
    }

var RecyclerView.diffedAdapter: RecyclerView.Adapter<*>?
    set(value) {
        if (adapter === value) return
        adapter = value
    }
    get() = adapter

val View.frameLayoutParams: FrameLayout.LayoutParams
    get() = layoutParams as FrameLayout.LayoutParams

fun EditText.addTextChangedListener(listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) = listener.invoke(p0.toString())
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    })
}

fun View.addOnFocusedListener(listener: () -> Unit) {
    setOnFocusChangeListener { editText, isFocused ->
        if (isFocused) listener.invoke()
    }
}

fun CheckBox.setOnCheckedChangeListener(listener: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked -> listener.invoke(isChecked) }
}

fun SwitchCompat.setOnCheckedChangeListener(listener: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked -> listener.invoke(isChecked) }
}

fun View.setOnClickListener(listener: () -> Unit) {
    setOnClickListener { listener.invoke() }
}

fun View.setOnFocusChangeListener(listener: (Boolean) -> Unit) {
    setOnFocusChangeListener { _, isFocused -> listener.invoke(isFocused) }
}

fun Toolbar.setNavigationOnClickListener(listener: () -> Unit) {
    setNavigationOnClickListener { listener.invoke() }
}

fun MaterialButton.switchEnabled(isEnabled: Boolean) {
    this.alpha = if (isEnabled) 1.0f else 0.4f
    this.isEnabled = isEnabled
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    imageTintList = resources.getColorStateList(colorRes)
}

fun Toolbar.setOnMenuItemClickListener(@IdRes menuItemId: Int, listener: () -> Unit) {
    menu.findItem(menuItemId)?.setOnMenuItemClickListener {
        listener.invoke()
        return@setOnMenuItemClickListener true
    }
}

fun <T> Observable<T>.distinctByValue(valueReceiver: () -> T): Observable<T> {
    return filter { it != valueReceiver.invoke() }
}

fun RecyclerView.init(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}


fun TextView.setTextColorCompat(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))

fun Button.setBackgroundTint(@ColorRes color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun TabLayout.switchTab(listener: (TabLayout.Tab) -> Unit){
    this.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {}
        override fun onTabUnselected(p0: TabLayout.Tab?) {}
        override fun onTabSelected(currentTab: TabLayout.Tab) {
            listener.invoke(currentTab)
        }
    })
}
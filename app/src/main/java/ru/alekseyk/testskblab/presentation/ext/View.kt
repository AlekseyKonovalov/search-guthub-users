package ru.alekseyk.testskblab.presentation.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout

var TextView.diffedValue: String
    get() = text.toString()
    set(value) {
        if (text.toString() == value) return
        text = value
    }

fun EditText.addTextChangedListener(listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) = listener.invoke(p0.toString())
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    })
}

fun Toolbar.setNavigationOnClickListener(listener: () -> Unit) {
    setNavigationOnClickListener { listener.invoke() }
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

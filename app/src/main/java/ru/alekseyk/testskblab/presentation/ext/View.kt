package ru.alekseyk.testskblab.presentation.ext

import android.view.KeyEvent
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

fun EditText.setOnKeyEnterListener(listener: (String) -> Unit) {
    this.setOnKeyListener { _, i, keyEvent ->
        if ((keyEvent.action == KeyEvent.ACTION_DOWN) &&
            (i == KeyEvent.KEYCODE_ENTER)
        ) {
            listener.invoke(this.text.toString())
            true
        }
        false
    }
}

fun Toolbar.setNavigationOnClickListener(listener: () -> Unit) {
    setNavigationOnClickListener { listener.invoke() }
}

fun TabLayout.switchTab(listener: (TabLayout.Tab) -> Unit) {
    this.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {}
        override fun onTabUnselected(p0: TabLayout.Tab?) {}
        override fun onTabSelected(currentTab: TabLayout.Tab) {
            listener.invoke(currentTab)
        }
    })
}

package ru.alekseyk.testskblab.presentation.ext

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun Context.getColorCompat(@ColorRes colorResource: Int): Int {
    return ContextCompat.getColor(this, colorResource)
}

fun Drawable.setTintCompat(context: Context, @ColorRes colorResource: Int) {
    setTint(context.getColorCompat(colorResource))
}

package ru.alekseyk.testskblab.presentation.ext

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    val density = this.resources
        .displayMetrics
        .density
    return (dp.toFloat() * density).roundToInt()
}

fun Context.pxToDp(size: Int): Int {
    val density = this.resources
        .displayMetrics
        .density
    return (size / density).toInt()
}

fun Context.getColorCompat(@ColorRes colorResource: Int): Int {
    return ContextCompat.getColor(this, colorResource)
}

fun Context.getDrawable(@DrawableRes drawable: Int, @ColorRes withTint: Int? = null) =
    getDrawable(drawable)!!.apply {
        withTint?.let { setTintCompat(this@getDrawable, it) }
    }


fun Drawable.setTintCompat(context: Context, @ColorRes colorResource: Int) {
    setTint(context.getColorCompat(colorResource))
}
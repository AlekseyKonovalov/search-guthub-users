package ru.alekseyk.testskblab.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindedViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}
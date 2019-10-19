package ru.alekseyk.testskblab.presentation.base.recycler

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alekseyk.testskblab.presentation.ext.inflate

abstract class BaseAdapter<VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {

    protected lateinit var context: Context
        private set

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = parent.inflate(onLayoutRequested(viewType), parent)
        return onCreateViewHolder(view, viewType)
    }

    abstract fun onLayoutRequested(viewType: Int): Int

    abstract fun onCreateViewHolder(view: View, viewType: Int): VH

}
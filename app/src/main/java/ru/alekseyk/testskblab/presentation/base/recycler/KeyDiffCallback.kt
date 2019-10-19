package ru.alekseyk.testskblab.presentation.base.recycler

import androidx.recyclerview.widget.DiffUtil

open class KeyDiffCallback<Item, Key>(
        private val oldItems: List<Item>,
        private val newItems: List<Item>,
        private val keyExtractor: (Item) -> Key
): DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return keyExtractor.invoke(oldItem) == keyExtractor.invoke(newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}
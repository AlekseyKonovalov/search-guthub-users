package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.list

import android.view.View
import kotlinx.android.synthetic.main.item_repositorysearch.view.*
import ru.alekseyk.testskblab.presentation.base.recycler.BaseViewHolder
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

internal class RepositoriesViewHolder(
    itemView: View,
    private val onItemClick: (Int) -> Unit
) : BaseViewHolder(itemView) {

    init {
        itemView.repositorysearch_item.setOnClickListener {
            if (hasPosition) onItemClick.invoke(adapterPosition)
        }
    }

    fun bind(item: RepositoryModel) {
        itemView.repositorysearch_item_text.text = item.name
    }


}
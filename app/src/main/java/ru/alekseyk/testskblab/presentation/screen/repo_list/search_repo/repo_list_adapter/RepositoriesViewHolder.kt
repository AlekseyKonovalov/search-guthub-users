package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repositorysearch.view.*
import ru.alekseyk.testskblab.presentation.base.recycler.BaseViewHolder
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

class RepositoriesViewHolder(
    itemView: View,
    private val onItemClick: (Int) -> Unit

) : BaseViewHolder(itemView) {

    init {
        itemView.repositorysearch_item.setOnClickListener {
            if (hasPosition) onItemClick.invoke(adapterPosition)
        }
    }

    fun bind(item: RepositoryModel) {
        Glide.with(context).load(item.ownerAvatarUrl).into(itemView.repositorysearch_item_image)
        itemView.repositorysearch_item_name.text = item.name
        itemView.repositorysearch_item_description.text = "Описание : ${item.description}"
        itemView.repositorysearch_item_owner_login.text = "Владелец : ${item.ownerLogin}"
    }


}
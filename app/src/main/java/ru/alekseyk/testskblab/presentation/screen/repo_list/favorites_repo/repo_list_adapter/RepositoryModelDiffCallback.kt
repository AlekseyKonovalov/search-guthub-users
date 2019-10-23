package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo.repo_list_adapter

import ru.alekseyk.testskblab.presentation.base.recycler.ItemDiffCallback
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

class RepositoryModelDiffCallback(
    oldItems: List<RepositoryModel>,
    newItems: List<RepositoryModel>
) : ItemDiffCallback<RepositoryModel>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepositoryModel,
        newItem: RepositoryModel
    ): Boolean {
        return  oldItem.isFavorite == newItem.isFavorite
    }

}
package ru.alekseyk.testskblab.presentation.screen.repo_list.repo_list_adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.recycler.BaseAdapter
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

internal class RepositoriesAdapter(
    private val onItemClick: (RepositoryModel) -> Unit,
    private val onActionClick: (RepositoryModel, Boolean) -> Unit
) : BaseAdapter<RepositoriesViewHolder>() {

    var items: List<RepositoryModel> = emptyList()
        set(value) {
            val callback = RepositoryModelDiffCallback(field, value)
            val result = DiffUtil.calculateDiff(callback)
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onLayoutRequested(viewType: Int) = R.layout.item_repositorysearch

    override fun onCreateViewHolder(view: View, viewType: Int) =
        RepositoriesViewHolder(
            view,
            ::onItemClick,
            ::onActionClick
        )

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) = holder.bind(
        items[position]
    )

    override fun getItemCount() = items.size

    private fun onItemClick(position: Int) = onItemClick.invoke(items[position])

    private fun onActionClick(position: Int, status: Boolean) {
        onActionClick.invoke(items[position], status)
    }




}
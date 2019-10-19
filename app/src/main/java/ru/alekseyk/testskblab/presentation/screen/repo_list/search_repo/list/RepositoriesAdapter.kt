package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.list

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.recycler.BaseAdapter
import ru.alekseyk.testskblab.presentation.base.recycler.KeyDiffCallback
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

internal class RepositoriesAdapter(
    private val onItemClick: (RepositoryModel) -> Unit
) : BaseAdapter<RepositoriesViewHolder>() {

    var items: List<RepositoryModel> = listOf()
        set(value) {
            val callback = KeyDiffCallback(field, value) { it.name }
            val result = DiffUtil.calculateDiff(callback)
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onLayoutRequested(viewType: Int) = R.layout.item_repositorysearch

    override fun onCreateViewHolder(view: View, viewType: Int) =
        RepositoriesViewHolder(
            view,
            ::onItemClick
        )

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) = holder.bind(
        items[position]
    )

    override fun getItemCount() = items.size

    private fun onItemClick(position: Int) = onItemClick.invoke(items[position])


}
package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_paging_footer.view.*
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.BindedViewHolder
import ru.alekseyk.testskblab.presentation.ext.inflate
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

class RepositoriesAdapter(
    private val onItemClick: (RepositoryModel) -> Unit,
    private val onRetryClickListener: () -> Unit
) : PagedListAdapter<RepositoryModel, RecyclerView.ViewHolder>(repositoryModelDiffCallback) {

    private var loadingState: PagingLoadingState? = null

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) VIEW_TYPE_DATA else VIEW_TYPE_FOOTER
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return (loadingState == PagingLoadingState.LOADING || loadingState == PagingLoadingState.ERROR)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        when (viewType) {
            VIEW_TYPE_DATA -> {
                val view = parent.inflate(R.layout.item_repositorysearch)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                holder = RepositoriesViewHolder(
                    view,
                    ::onItemClick
                )
            }
            VIEW_TYPE_FOOTER -> {
                val view = parent.inflate(R.layout.item_paging_footer)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                holder = FooterViewHolder(view)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoriesViewHolder) {
            val item = getItem(position)
            if (item != null) {
                holder.bind(item)
            }
        } else {
            (holder as FooterViewHolder).bind(loadingState)
            holder.setOnRetryClickListener {
                onRetryClickListener.invoke()
            }
        }
    }



    private fun onItemClick(position: Int) {
        getItem(position)?.let { onItemClick.invoke(it) }
    }


    fun setLoadingState(loadingState: PagingLoadingState) {
        this.loadingState = loadingState
        notifyItemChanged(itemCount)
    }

    inner class FooterViewHolder(val view: View) : BindedViewHolder<PagingLoadingState?>(view) {

        override fun bind(data: PagingLoadingState?) {
            when (data) {
                PagingLoadingState.LOADING -> {
                    view.message.text = "Загрузка..."
                    view.progress_bar.visibility = View.VISIBLE
                    view.repeat.visibility = View.GONE
                }
                PagingLoadingState.ERROR -> {
                    view.message.text = "Ошибка при загрузке"
                    view.progress_bar.visibility = View.GONE
                    view.repeat.visibility = View.VISIBLE
                }
                else -> {
                    view.message.text = null
                    view.progress_bar.visibility = View.GONE
                    view.repeat.visibility = View.GONE
                }
            }
        }

        fun setOnRetryClickListener(callback: () -> Unit) {
            view.repeat.setOnClickListener {
                callback()
            }
        }

    }

    companion object {
        private const val VIEW_TYPE_DATA = 1
        private const val VIEW_TYPE_FOOTER = 2

        val repositoryModelDiffCallback = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(
                oldItem: RepositoryModel,
                newItem: RepositoryModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RepositoryModel,
                newItem: RepositoryModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }


}
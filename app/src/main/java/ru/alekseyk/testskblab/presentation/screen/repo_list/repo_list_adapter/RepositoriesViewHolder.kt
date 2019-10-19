package ru.alekseyk.testskblab.presentation.screen.repo_list.repo_list_adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repositorysearch.view.*
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.recycler.BaseViewHolder
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

internal class RepositoriesViewHolder(
    itemView: View,
    private val onItemClick: (Int) -> Unit,
    private val onActionClick: ((Int, Boolean) -> Unit)
) : BaseViewHolder(itemView) {

    private var isStarred: Boolean = false

    init {
        itemView.repositorysearch_item.setOnClickListener {
            if (hasPosition) onItemClick.invoke(adapterPosition)
        }
        itemView.repositorysearch_item_star.setOnClickListener {
            isStarred = !isStarred
            if (hasPosition) onActionClick.invoke(adapterPosition, isStarred)

            (it as ImageView).setImageDrawable(
                if (isStarred) context.getDrawable(R.drawable.ic_star)
                else context.getDrawable(R.drawable.ic_star_border)
            )

        }
    }

    fun bind(item: RepositoryModel) {
        isStarred = item.isFavorite

        Glide.with(context).load(item.ownerAvatarUrl).into(itemView.repositorysearch_item_image)
        itemView.repositorysearch_item_star.setImageDrawable(
            if (isStarred) context.getDrawable(R.drawable.ic_star)
            else context.getDrawable(R.drawable.ic_star_border)
        )
        itemView.repositorysearch_item_name.text = item.name
        itemView.repositorysearch_item_description.text = "Description : ${item.description}"
        itemView.repositorysearch_item_owner_login.text = "Owner : ${item.ownerLogin}"

    }


}
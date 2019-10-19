package ru.alekseyk.testskblab.data.dto


import com.google.gson.annotations.SerializedName

data class SearchRepositoriesListDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<RepositoryItem>?,
    @SerializedName("total_count")
    val totalCount: Int
)
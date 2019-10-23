package ru.alekseyk.testskblab.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto


interface Api {

    // repositories
    @GET("/search/repositories")
    fun searchRepositoriesByQuery(@Query("q") query: String,
                                  @Query("page") page: Int,
                                  @Query("per_page") count: Int

                                 ): Observable<SearchRepositoriesListDto>


}
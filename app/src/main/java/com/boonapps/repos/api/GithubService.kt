package com.boonapps.repos.api

import androidx.lifecycle.LiveData
import com.boonapps.repos.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {


    @GET("search/repositories")
    suspend fun getRepo(
        @Query("q") query: String = "language=+sort:stars"
    ): GithubApiResponse


}
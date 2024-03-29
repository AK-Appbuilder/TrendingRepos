package com.boonapps.repos.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    suspend fun getRepo(
        @Query("q") query: String = "language=+sort:stars"
    ): GithubApiResponse
}
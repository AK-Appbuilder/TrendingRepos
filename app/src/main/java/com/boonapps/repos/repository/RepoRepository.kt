package com.boonapps.repos.repository

import com.boonapps.repos.Result
import com.boonapps.repos.api.GithubService
import com.boonapps.repos.callApi
import com.boonapps.repos.models.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepoRepository(private val githubService: GithubService) {


    fun loadRepos() : Flow<Result<Repo>> = flow {

        emit(Result.Loading)

        emit(callApi { githubService.getRepo() })
    }
}
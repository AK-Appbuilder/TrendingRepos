package com.boonapps.repos.repository

import com.boonapps.repos.Result
import com.boonapps.repos.api.GithubService
import com.boonapps.repos.callApi
import com.boonapps.repos.models.Repo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepoRepository(
    private val githubService: GithubService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

     @ExperimentalCoroutinesApi
     fun loadRepos(): Flow<Result<List<Repo>>> = flow {

         emit(Result.Loading)
         emit(callApi { githubService.getRepo().items })

    }.flowOn(dispatcher)

}
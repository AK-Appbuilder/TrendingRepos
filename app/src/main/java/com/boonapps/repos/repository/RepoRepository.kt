package com.boonapps.repos.repository

import com.boonapps.repos.api.GithubService
import com.boonapps.repos.db.RepoDao
import com.boonapps.repos.models.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepoRepository(
        private val githubService: GithubService,
        private val repoDao: RepoDao,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    @ExperimentalCoroutinesApi
    fun loadRepos(): Flow<Result<List<Repo>>> = flow {

        emit(Result.Loading)
        val networkResult = callApi { githubService.getRepo().items }

        if (networkResult.succeeded()) {
            networkResult.data?.let { repoDao.insertAll(it) }
            emit(networkResult)
        } else {
            val dbResult = repoDao.getAll()
            emit(Result.Success(dbResult))
        }

    }.flowOn(dispatcher)

}
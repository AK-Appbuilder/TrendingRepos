package com.boonapps.repos.repository

import com.boonapps.repos.Result
import com.boonapps.repos.api.GithubService
import com.boonapps.repos.callApi
import com.boonapps.repos.models.Repo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RepoRepository(
    private val githubService: GithubService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun loadRepos(): Result<List<Repo>> {
        return withContext(dispatcher) {
            callApi {
                githubService.getRepo().items
            }
        }
    }
}
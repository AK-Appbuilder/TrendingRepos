package com.boonapps.repos.viewmodel

import com.boonapps.repos.CoroutineTestRule
import com.boonapps.repos.api.GithubApiResponse
import com.boonapps.repos.api.GithubService
import com.boonapps.repos.db.RepoDao
import com.boonapps.repos.models.Repo
import com.boonapps.repos.repository.RepoRepository
import com.boonapps.repos.runBlockingTest
import kotlinx.coroutines.flow.collectLatest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException
import kotlin.text.Typography.times

class RepoRepositoryTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var githubService: GithubService

    @Mock
    private lateinit var repoDao: RepoDao

    private lateinit var repoRepository: RepoRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repoRepository = RepoRepository(githubService, repoDao, coroutineRule.testDispatcher)
    }

    @Test
    fun `load repos from cache when internet not available`(){
        coroutineRule.runBlockingTest {

            Mockito.`when`(repoDao.getAll()).thenReturn(createReposList())

            Mockito.`when`(githubService.getRepo())
                    .thenThrow(RuntimeException("Failed to load repos"))

            repoRepository.loadRepos().collectLatest {}

            Mockito.verify(repoDao).getAll()
        }
    }


    @Test
    fun `load repos from network when internet is available`(){
        coroutineRule.runBlockingTest {

            Mockito.`when`(repoDao.getAll()).thenReturn(createReposList())

            Mockito.`when`(githubService.getRepo()).thenReturn(createFakeResponse())

            repoRepository.loadRepos().collectLatest {}

            Mockito.verify(repoDao, times(0)).getAll()
        }
    }

    private fun createFakeResponse() = GithubApiResponse(2, createReposList())

    private fun createReposList() : List<Repo>{

        val owner1 =
                Repo.Owner(
                        "wenyan-lang",
                        "https://avatars.githubusercontent.com/u/59635288?v=4",null)
        val repo1 =
                Repo(
                        12244,
                        "wenyan",
                        "wenyan-lang/wenya",
                        "ancient chinees",
                        owner1,
                        "1132",
                        "script"
                )

        val owner2 =
                Repo.Owner(
                        "go-delve",
                        "https://avatars.githubusercontent.com/u/19232073?v=4", null)

        val repo2 =
                Repo(
                        3356,
                        "delve",
                        "go-delve/wenya",
                        "Debugger for the go programming langauge",
                        owner2,
                        "100",
                        "go"
                )

        return listOf<Repo>(repo1, repo2)
    }


}
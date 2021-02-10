package com.boonapps.repos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.boonapps.repos.CoroutineTestRule
import com.boonapps.repos.api.GithubApiResponse
import com.boonapps.repos.api.GithubService
import com.boonapps.repos.models.Repo
import com.boonapps.repos.models.Result
import com.boonapps.repos.models.data
import com.boonapps.repos.repository.RepoRepository
import com.boonapps.repos.runBlockingTest
import com.boonapps.repos.waitUntilConditionFulfilled
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class RepoViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var githubService: GithubService

    private lateinit var repoRepository: RepoRepository
    private lateinit var repoViewModel: RepoViewModel


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repoRepository =
            RepoRepository(githubService, coroutineRule.testDispatcher)
        repoViewModel = RepoViewModel(repoRepository)
    }

    @Test
    fun `test loading repo successfully`() {
        coroutineRule.runBlockingTest {

            Mockito.`when`(githubService.getRepo())
                .thenReturn(createFakeResponse())

            repoViewModel.loadRepos()

            repoViewModel.result.waitUntilConditionFulfilled { it is Result.Success }

            Truth.assertThat(repoViewModel.result.value?.data?.size).isEqualTo(2)
            Truth.assertThat(repoViewModel.result.value?.data?.get(0)?.name).isEqualTo("wenyan")
            Truth.assertThat(repoViewModel.result.value?.data?.get(1)?.name).isEqualTo("delve")

            verify(githubService).getRepo()
        }
    }


    @Test
    fun `test no repos  available`() {
        coroutineRule.runBlockingTest {
            Mockito.`when`(githubService.getRepo())
                    .thenReturn(GithubApiResponse(112, emptyList()))

            repoViewModel.loadRepos()

            repoViewModel.result.waitUntilConditionFulfilled { it is Result.Empty}

            Truth.assertThat(repoViewModel.result.value).isEqualTo(Result.Empty)

            verify(githubService).getRepo()
        }
    }

    @Test
    fun `tests repos loading failed`() {
        coroutineRule.runBlockingTest {

            Mockito.`when`(githubService.getRepo())
                .thenThrow(RuntimeException("Failed to load repos"))

            repoViewModel.loadRepos()

            repoViewModel.result.waitUntilConditionFulfilled { it is Result.Error}

            Truth.assertThat(repoViewModel.result.value).isInstanceOf(Result.Error::class.java)
        }
    }

    private fun createFakeResponse(): GithubApiResponse {
        val owner1 =
            Repo.Owner(
                "wenyan-lang",
                "https://avatars.githubusercontent.com/u/59635288?v=4"
            )

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
                "https://avatars.githubusercontent.com/u/19232073?v=4"
            )

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


        return GithubApiResponse(2, listOf(repo1, repo2))

    }

}
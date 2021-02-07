package com.boonapps.repos

import com.boonapps.repos.api.GithubService
import com.boonapps.repos.repository.RepoRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {

    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }


    fun provideRepoRepository (): RepoRepository {
          return RepoRepository(provideGithubService())
    }
}
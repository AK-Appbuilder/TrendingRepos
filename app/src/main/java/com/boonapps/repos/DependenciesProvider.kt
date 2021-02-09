package com.boonapps.repos

import com.boonapps.repos.api.GithubService
import com.boonapps.repos.interceptors.NoConnectionInterceptor
import com.boonapps.repos.repository.RepoRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object DependenciesProvider {

    private const val BASE_URL = "https://api.github.com/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideGithubClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                NoConnectionInterceptor(App.context))
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideGithubClient())
            .build()
            .create(GithubService::class.java)
    }

    fun provideRepoRepository(): RepoRepository {
        return RepoRepository(provideGithubService())
    }
}
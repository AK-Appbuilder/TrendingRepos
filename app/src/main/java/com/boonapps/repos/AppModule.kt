package com.boonapps.repos

import com.boonapps.repos.api.GithubService
import com.boonapps.repos.repository.RepoRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun provideGithubClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NoConnectionInterceptor(App.context))
            .addInterceptor(interceptor).apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(StethoInterceptor())
                }
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideGithubClient())
            .build()
            .create(GithubService::class.java)
    }


    fun provideRepoRepository(): RepoRepository {
        return RepoRepository(provideGithubService())
    }
}
package com.boonapps.repos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boonapps.repos.Event
import com.boonapps.repos.Result
import com.boonapps.repos.models.Repo
import com.boonapps.repos.repository.RepoRepository

class RepoViewModel(val repoRepository: RepoRepository) : ViewModel() {

    private val _repos = MutableLiveData<Repo>()
    private val repos: LiveData<Repo> = _repos

    // Mutable/LiveData of String resource reference Event
    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> = _error


    fun getRepos(){

        repoRepository.loadRepos()
    }


    fun handleResponse(result: Result<*>, success: (data: Any?) -> Unit) {
        _loading.value = false

        when (result) {

            is Result.Success -> {
                success(result.data)
            }

            is Result.Error -> {
                _message.value = Event(result.exception.message!!)
                _error.value = Event(true)
            }

            else -> {

            }
        }
    }

}
package com.boonapps.repos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boonapps.repos.Event
import com.boonapps.repos.Result
import com.boonapps.repos.models.Repo
import com.boonapps.repos.repository.RepoRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RepoViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>> = _repos

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _empty = MutableLiveData<Boolean>()
    val empty : LiveData<Boolean> = _empty

    private val _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> = _error

    fun getRepos() {
        viewModelScope.launch {
            _loading.value = true

            repoRepository.loadRepos().collectLatest { result ->
                _loading.value = false

                when (result) {

                    is Result.Success -> {
                       _repos.value = result.data
                    }

                    is Result.Empty -> {
                      _empty.value = true
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
    }

}
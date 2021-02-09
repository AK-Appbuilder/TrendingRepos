package com.boonapps.repos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boonapps.repos.models.Result
import com.boonapps.repos.models.Repo
import com.boonapps.repos.repository.RepoRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RepoViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    private val _result = MutableLiveData<Result<List<Repo>>>()
    val result: LiveData<Result<List<Repo>>> = _result

    fun retry() {
        loadRepos()
    }

    fun loadRepos() {

        viewModelScope.launch {
            repoRepository.loadRepos().collectLatest {
                _result.value = it
            }
        }
    }
}
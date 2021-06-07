package com.marko.githubapp.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.githubapp.domain.Repo
import com.marko.githubapp.repository.repos.GitHubReposRepository
import com.marko.githubapp.util.Constants.USERNAME
import com.marko.githubapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val reposRepository: GitHubReposRepository
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<DataState<List<Repo>>>()
    val reposLiveData: LiveData<DataState<List<Repo>>> = _reposLiveData

    init {
        fetchUserRepos()
    }

    private fun fetchUserRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            reposRepository.fetchUserRepositories(USERNAME).collect {
                _reposLiveData.postValue(it)
            }
        }
    }
}
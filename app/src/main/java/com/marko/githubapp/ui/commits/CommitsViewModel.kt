package com.marko.githubapp.ui.commits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.repository.commit.CommitsRepository
import com.marko.githubapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommitsViewModel @Inject constructor(
    private val commitsRepository: CommitsRepository
): ViewModel() {

    private val _commitsLiveData = MutableLiveData<DataState<List<Commit>>>()
    val commitsLiveData: LiveData<DataState<List<Commit>>> = _commitsLiveData

    fun fetchCommitsForRepo(repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            commitsRepository.fetchCommitsForRepository(repo).collect { result ->
                _commitsLiveData.postValue(result)
            }
        }
    }
}
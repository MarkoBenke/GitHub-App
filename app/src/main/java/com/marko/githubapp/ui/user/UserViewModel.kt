package com.marko.githubapp.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.githubapp.domain.User
import com.marko.githubapp.repository.user.UserRepository
import com.marko.githubapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userLiveData = MutableLiveData<DataState<User>>()
    val userLiveData: LiveData<DataState<User>> = _userLiveData

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.fetchUser("MarkoBenke").collect {
                _userLiveData.postValue(it)
            }
        }
    }
}
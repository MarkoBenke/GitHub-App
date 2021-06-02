package com.marko.githubapp.repository.user

import com.marko.githubapp.domain.User
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchUser(username: String): Flow<DataState<User>>
}
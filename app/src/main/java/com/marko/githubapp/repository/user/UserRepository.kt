package com.marko.githubapp.repository.user

import com.marko.githubapp.domain.User
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for User. All the interaction methods for User segment will be placed here.
 */
interface UserRepository {

    suspend fun fetchUser(username: String): Flow<DataState<User>>
}
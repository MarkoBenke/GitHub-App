package com.marko.githubapp.repository.user

import com.marko.githubapp.domain.User
import com.marko.githubapp.network.mappers.UserMapper
import com.marko.githubapp.network.service.UserService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

class DefaultUserRepository constructor(
    private val userService: UserService,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun fetchUser(username: String): Flow<DataState<User>> {
        TODO("Not yet implemented")
    }
}
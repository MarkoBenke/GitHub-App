package com.marko.githubapp.repository.user

import com.marko.githubapp.domain.User
import com.marko.githubapp.network.mappers.UserMapper
import com.marko.githubapp.network.service.UserService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultUserRepository constructor(
    private val userService: UserService,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun fetchUser(username: String): Flow<DataState<User>> = flow {
        emit(DataState.Loading)

        try {
            val response = userService.fetchUser()
            if (response.isSuccessful) {
                response.body()?.let { userResult ->
                    emit(DataState.Success(userMapper.mapFromDto(userResult)))
                }
            } else {
                emit(DataState.Error())
            }
        } catch (e: Exception) {
            emit(DataState.Error())
        }
    }
}
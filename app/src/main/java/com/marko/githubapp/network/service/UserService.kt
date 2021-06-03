package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.UserDto
import com.marko.githubapp.network.util.NetworkUrl.USER
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET(USER)
    suspend fun fetchUser(): Response<UserDto>
}
package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.UserDto
import com.marko.githubapp.network.util.NetworkUrl.GET_USER
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET(GET_USER)
    suspend fun fetchUser(): Response<UserDto>
}
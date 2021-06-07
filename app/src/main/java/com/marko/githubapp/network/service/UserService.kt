package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.UserDto
import com.marko.githubapp.network.util.NetworkUrl.GET_USER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    /**
     * It fetches GitHub user by provided username
     *
     * @param username username of the user
     */
    @GET(GET_USER)
    suspend fun fetchUser(@Path("user") username: String): Response<UserDto>
}
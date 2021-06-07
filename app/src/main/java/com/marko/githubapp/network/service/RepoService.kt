package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.RepoDto
import com.marko.githubapp.network.util.NetworkUrl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    /**
     * It fetches all public repository for desired user
     *
     * @param username username of the user
     */
    @GET(NetworkUrl.GET_USER_REPOS)
    suspend fun fetchUserRepos(@Path("user") username: String): Response<List<RepoDto>>
}
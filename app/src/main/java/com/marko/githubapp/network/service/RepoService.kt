package com.marko.githubapp.network.service

import com.marko.githubapp.network.util.NetworkUrl
import retrofit2.http.GET

interface RepoService {

    @GET(NetworkUrl.USER_REPOS)
    suspend fun fetchUserRepos()
}
package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.commit.CommitDto
import com.marko.githubapp.network.util.NetworkUrl.GET_REPO_COMMITS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitsService {

    @GET(GET_REPO_COMMITS)
    suspend fun fetchCommitsForRepo(@Path("repo") repo: String): Response<List<CommitDto>>
}
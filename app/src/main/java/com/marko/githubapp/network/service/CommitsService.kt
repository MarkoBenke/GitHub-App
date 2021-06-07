package com.marko.githubapp.network.service

import com.marko.githubapp.network.dto.commit.CommitDto
import com.marko.githubapp.network.util.NetworkUrl.GET_REPO_COMMITS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitsService {

    /**
     * It fetches all commits for desired repository
     *
     * @param username username of the user
     * @param repo selected user's GitHub repository
     */
    @GET(GET_REPO_COMMITS)
    suspend fun fetchCommitsForRepo(
        @Path("user") username: String,
        @Path("repo") repo: String
    ): Response<List<CommitDto>>
}
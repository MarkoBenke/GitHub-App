package com.marko.githubapp.repository.repos

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

interface GitHubReposRepository {

    suspend fun fetchUserRepositories(username: String): Flow<DataState<List<Repo>>>
}
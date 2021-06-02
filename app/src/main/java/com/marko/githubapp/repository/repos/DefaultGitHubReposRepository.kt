package com.marko.githubapp.repository.repos

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

class DefaultGitHubReposRepository: GitHubReposRepository {

    override suspend fun fetchUserRepositories(username: String): Flow<DataState<List<Repo>>> {
        TODO("Not yet implemented")
    }
}
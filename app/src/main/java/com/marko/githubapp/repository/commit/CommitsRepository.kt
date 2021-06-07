package com.marko.githubapp.repository.commit

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow

interface CommitsRepository {

    suspend fun fetchCommitsForRepository(
        username: String,
        repo: String
    ): Flow<DataState<List<Commit>>>
}
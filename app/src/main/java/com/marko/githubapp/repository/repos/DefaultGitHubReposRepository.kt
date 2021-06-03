package com.marko.githubapp.repository.repos

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.network.mappers.RepoMapper
import com.marko.githubapp.network.service.RepoService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultGitHubReposRepository constructor(
    private val repoService: RepoService,
    private val repoMapper: RepoMapper
) : GitHubReposRepository {

    override suspend fun fetchUserRepositories(username: String): Flow<DataState<List<Repo>>> =
        flow {
            emit(DataState.Loading)

            try {
                val response = repoService.fetchUserRepos()

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(DataState.Success(repoMapper.mapFromDtoList(it)))
                    }
                } else {
                    emit(DataState.Error())
                }
            } catch (e: Exception) {
                emit(DataState.Error())
            }
        }
}
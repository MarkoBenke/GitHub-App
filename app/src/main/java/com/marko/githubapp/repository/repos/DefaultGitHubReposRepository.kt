package com.marko.githubapp.repository.repos

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.network.mappers.RepoMapper
import com.marko.githubapp.network.service.RepoService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation class of GitHubReposRepository interface.
 *
 * @param repoService repo service
 * @param repoMapper repo mapper
 */
class DefaultGitHubReposRepository constructor(
    private val repoService: RepoService,
    private val repoMapper: RepoMapper
) : GitHubReposRepository {

    /**
     * It fetches all the public repositories of desired GitHub user.
     *
     * @param username GitHub username
     */
    override suspend fun fetchUserRepositories(username: String): Flow<DataState<List<Repo>>> =
        flow {
            emit(DataState.Loading)

            try {
                val response = repoService.fetchUserRepos(username)

                if (response.isSuccessful) {
                    response.body()?.let { reposResult ->
                        emit(DataState.Success(repoMapper.mapFromDtoList(reposResult)))
                    }
                } else {
                    emit(DataState.Error())
                }
            } catch (e: Exception) {
                emit(DataState.Error())
            }
        }
}
package com.marko.githubapp.repository.commit

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.network.mappers.CommitsMapper
import com.marko.githubapp.network.service.CommitsService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of CommitsRepository interface.
 *
 * @param commitsService commits service
 * @param commitsMapper commits mapper
 */
class DefaultCommitsRepository @Inject constructor(
    private val commitsService: CommitsService,
    private val commitsMapper: CommitsMapper
) : CommitsRepository {

    /**
     * It fetches all the commits of desired GitHub repository.
     *
     * @param username GitHub username
     * @param repo GitHub repository
     */
    override suspend fun fetchCommitsForRepository(
        username: String,
        repo: String
    ): Flow<DataState<List<Commit>>> = flow {
        emit(DataState.Loading)

        try {
            val response = commitsService.fetchCommitsForRepo(username, repo)

            if (response.isSuccessful) {
                response.body()?.let { commitsResult ->
                    emit(DataState.Success(commitsMapper.mapFromDtoList(commitsResult)))
                }
            } else {
                emit(DataState.Error())
            }
        } catch (e: Exception) {
            emit(DataState.Error())
        }
    }
}
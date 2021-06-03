package com.marko.githubapp.repository.commit

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.network.mappers.CommitsMapper
import com.marko.githubapp.network.service.CommitsService
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultCommitsRepository constructor(
    private val commitsService: CommitsService,
    private val commitsMapper: CommitsMapper
): CommitsRepository {

    override suspend fun fetchCommitsForRepository(repo: String): Flow<DataState<List<Commit>>> = flow {
        emit(DataState.Loading)

        try {
            val response = commitsService.fetchCommitsForRepo(repo)

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
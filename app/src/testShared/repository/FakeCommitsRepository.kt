package repository

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.repository.commit.CommitsRepository
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.DataGenerator

class FakeCommitsRepository(private val shouldReturnError: Boolean = false) : CommitsRepository {

    override suspend fun fetchCommitsForRepository(repo: String): Flow<DataState<List<Commit>>> =
        flow {
            if (shouldReturnError) {
                emit(DataState.Error("Error"))
                return@flow
            }
            emit(DataState.Success(DataGenerator.commits))
        }
}
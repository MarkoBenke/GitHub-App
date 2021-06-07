package shared.repository

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.repository.commit.CommitsRepository
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shared.util.DataGenerator
import javax.inject.Inject

/**
 * Class that is used to replace DefaultCommitsRepository class in tests.
 */
class FakeCommitsRepository @Inject constructor(private val shouldReturnError: Boolean) :
    CommitsRepository {

    override suspend fun fetchCommitsForRepository(
        username: String,
        repo: String
    ): Flow<DataState<List<Commit>>> =
        flow {
            if (shouldReturnError) {
                emit(DataState.Error("Error"))
                return@flow
            }
            emit(DataState.Success(DataGenerator.commits))
        }
}
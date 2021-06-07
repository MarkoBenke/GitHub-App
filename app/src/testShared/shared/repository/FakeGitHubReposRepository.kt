package shared.repository

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.repository.repos.GitHubReposRepository
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shared.util.DataGenerator
import javax.inject.Inject

/**
 * Class that is used to replace DefaultGitHubReposRepository class in tests.
 */
class FakeGitHubReposRepository @Inject constructor(private val shouldReturnError: Boolean) :
    GitHubReposRepository {

    override suspend fun fetchUserRepositories(username: String): Flow<DataState<List<Repo>>> =
        flow {
            if (shouldReturnError) {
                emit(DataState.Error("Error"))
                return@flow
            }
            emit(DataState.Success(DataGenerator.repos))
        }
}
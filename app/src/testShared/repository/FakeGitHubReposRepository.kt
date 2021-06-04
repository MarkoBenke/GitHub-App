package repository

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.repository.repos.GitHubReposRepository
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.DataGenerator

class FakeGitHubReposRepository(private val shouldReturnError: Boolean = false) :
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
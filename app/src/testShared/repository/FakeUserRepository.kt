package repository

import com.marko.githubapp.domain.User
import com.marko.githubapp.repository.user.UserRepository
import com.marko.githubapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.DataGenerator

class FakeUserRepository(private val shouldReturnError: Boolean = false): UserRepository {

    override suspend fun fetchUser(username: String): Flow<DataState<User>> = flow {
        if (shouldReturnError) {
            emit(DataState.Error("Error"))
            return@flow
        }
        emit(DataState.Success(DataGenerator.user))
    }
}
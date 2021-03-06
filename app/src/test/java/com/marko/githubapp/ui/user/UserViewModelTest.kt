package com.marko.githubapp.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.marko.githubapp.util.DataState
import com.marko.githubapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import shared.repository.FakeUserRepository
import shared.util.DataGenerator
import shared.util.getOrAwaitValueTest

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: UserViewModel

    @Test
    fun `fetch user, returns success`() {
        viewModel = UserViewModel(FakeUserRepository(shouldReturnError = false))

        val result = viewModel.userLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Success(DataGenerator.user))
    }

    @Test
    fun `fetch user, returns error`() {
        viewModel = UserViewModel(FakeUserRepository(shouldReturnError = true))

        val result = viewModel.userLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Error("Error"))
    }
}
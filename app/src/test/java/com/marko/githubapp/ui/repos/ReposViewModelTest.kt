package com.marko.githubapp.ui.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.marko.githubapp.util.DataState
import com.marko.githubapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import repository.FakeGitHubReposRepository
import util.DataGenerator
import util.getOrAwaitValueTest

@ExperimentalCoroutinesApi
class ReposViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ReposViewModel

    @Test
    fun `fetch repos, returns success`() {
        viewModel = ReposViewModel(FakeGitHubReposRepository())

        val result = viewModel.reposLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Success(DataGenerator.repos))
    }

    @Test
    fun `fetch repos, returns error`() {
        viewModel = ReposViewModel(FakeGitHubReposRepository(shouldReturnError = true))

        val result = viewModel.reposLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Error("Error"))
    }
}
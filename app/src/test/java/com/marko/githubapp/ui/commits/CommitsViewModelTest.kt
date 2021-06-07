package com.marko.githubapp.ui.commits

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.marko.githubapp.util.DataState
import com.marko.githubapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import shared.repository.FakeCommitsRepository
import shared.util.DataGenerator
import shared.util.getOrAwaitValueTest

@ExperimentalCoroutinesApi
class CommitsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CommitsViewModel

    @Test
    fun `fetch commits, returns success`() {
        viewModel = CommitsViewModel(FakeCommitsRepository(shouldReturnError = false))
        viewModel.fetchCommitsForRepo("")
        val result = viewModel.commitsLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Success(DataGenerator.commits))
    }

    @Test
    fun `fetch commits, returns error`() {
        viewModel = CommitsViewModel(FakeCommitsRepository(shouldReturnError = true))
        viewModel.fetchCommitsForRepo("")
        val result = viewModel.commitsLiveData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(DataState.Error("Error"))
    }
}
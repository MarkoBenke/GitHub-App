package com.marko.githubapp.ui.repos

import com.marko.githubapp.R
import com.marko.githubapp.di.FakeRepositoryModule
import com.marko.githubapp.domain.Repo
import com.marko.githubapp.util.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Rule
import org.junit.Test
import shared.util.DataGenerator

@ExperimentalCoroutinesApi
@HiltAndroidTest
class ReposFragmentTest : BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @After
    fun teardown() {
        FakeRepositoryModule.shouldReturnError = false
    }

    @Test
    fun checkUi() {
        launchReposFragment()
        checkRecyclerViewItemCount(R.id.reposRecView, 3)

        checkTextOnRepoItem(R.id.title, getRepo().name)
        checkTextOnRepoItem(R.id.description, getRepo().description!!)
        checkTextOnRepoItem(R.id.language, getRepo().language!!)
        checkTextOnRepoItem(
            R.id.openIssues, context.getString(
                R.string.open_issues_count,
                getRepo().openIssuesCount
            )
        )
    }

    @Test
    fun checkErrorUi() {
        FakeRepositoryModule.shouldReturnError = true
        launchReposFragment()

        isTextVisible(R.string.unknown_error_title)
        isTextVisible(R.string.unknown_error_message)
    }

    private fun launchReposFragment() {
        hiltRule.inject()
        launchFragmentInHiltContainer<ReposFragment> {}
    }

    private fun getRepo(): Repo = DataGenerator.repos[FIRST_POSITION]

    private fun checkTextOnRepoItem(itemId: Int, text: String) {
        checkTextOnRecyclerViewItem(
            R.id.reposRecView,
            itemId,
            FIRST_POSITION,
            text
        )
    }
}
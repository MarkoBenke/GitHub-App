package com.marko.githubapp.ui.commits

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import com.marko.githubapp.R
import com.marko.githubapp.di.FakeRepositoryModule
import com.marko.githubapp.ui.repos.ReposFragment
import com.marko.githubapp.util.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Rule
import org.junit.Test
import shared.util.DataGenerator

@ExperimentalCoroutinesApi
@HiltAndroidTest
class CommitsFragmentTest: BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @After
    fun teardown() {
        FakeRepositoryModule.shouldReturnError = false
    }

    @Test
    fun checkUi() {
        launchCommitsFragment()
        sleepShort()
        checkRecyclerViewItemCount(R.id.commitsRecView, 3)
        checkTextOnRecyclerViewItem(
            R.id.commitsRecView,
            R.id.authorName,
            FIRST_POSITION,
            DataGenerator.commits[FIRST_POSITION].commitData.authorName
        )
        checkTextOnRecyclerViewItem(
            R.id.commitsRecView,
            R.id.date,
            FIRST_POSITION,
            DataGenerator.commits[FIRST_POSITION].commitData.commitDate
        )
        checkTextOnRecyclerViewItem(
            R.id.commitsRecView,
            R.id.commitMessage,
            FIRST_POSITION,
            DataGenerator.commits[FIRST_POSITION].commitData.message!!
        )
        checkTextOnRecyclerViewItem(
            R.id.commitsRecView,
            R.id.sha,
            FIRST_POSITION,
            DataGenerator.commits[FIRST_POSITION].sha
        )
        checkTextOnRecyclerViewItem(
            R.id.commitsRecView,
            R.id.numOfComments,
            FIRST_POSITION,
            context.getString(
                R.string.comments_number,
                DataGenerator.commits[FIRST_POSITION].commitData.commentCount
            )
        )
    }

    @Test
    fun checkIfCommitUrlOpens() {
        Intents.init()
        launchCommitsFragment()
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData("https://www.google.rs"))
        clickOnRecyclerViewItem(R.id.commitsRecView, R.id.openCommit, FIRST_POSITION)

        intended(expectedIntent)
        Intents.release()
    }

    @Test
    fun checkErrorUi() {
        FakeRepositoryModule.shouldReturnError = true
        launchCommitsFragment()

        isTextVisible(R.string.unknown_error_title)
        isTextVisible(R.string.unknown_error_message)
    }

    private fun launchCommitsFragment() {
        hiltRule.inject()
        val bundle = bundleOf(
            ReposFragment.REPO_KEY to DataGenerator.repos[FIRST_POSITION]
        )
        launchFragmentInHiltContainer<CommitsFragment>(fragmentArgs = bundle) {}
    }
}
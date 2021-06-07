package com.marko.githubapp.ui.user

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.pressBack
import com.marko.githubapp.R
import com.marko.githubapp.util.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class UserActivityTest: BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun checkNavigationToCommits() {
        launchUserActivity()

        navigateToCommits()
    }

    @Test
    fun checkNavigationBackFromCommits() {
        launchUserActivity()

        navigateToCommits()
        pressBack()
        isViewVisible(R.id.reposRecView)
        pressBack()
        isViewVisible(R.id.userName)
    }

    private fun navigateToCommits() {
        isViewVisible(R.id.userName)
        clickOnView(R.id.repoButton)
        sleepShort()
        isViewVisible(R.id.reposRecView)
        clickOnRecyclerViewItem(R.id.reposRecView, R.id.title, FIRST_POSITION)
        isViewVisible(R.id.commitsRecView)
    }

    private fun launchUserActivity() {
        hiltRule.inject()
        ActivityScenario.launch(UserActivity::class.java)
    }
}
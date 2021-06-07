package com.marko.githubapp.ui.user

import com.marko.githubapp.R
import com.marko.githubapp.di.FakeRepositoryModule
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
class UserFragmentTest: BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @After
    fun teardown() {
        FakeRepositoryModule.shouldReturnError = false
    }

    @Test
    fun checkUi() {
        launchUserFragment()

        isViewVisible(R.id.profileImage)
        isViewVisible(R.id.repoButton)

        checkTextOnView(R.id.userName, DataGenerator.user.name)
        checkTextOnView(R.id.userCompany, DataGenerator.user.company!!)
        checkTextOnView(R.id.userBio, DataGenerator.user.bio!!)
        checkTextOnView(R.id.followersCount, DataGenerator.user.followers.toString())
        checkTextOnView(R.id.followingCount, DataGenerator.user.following.toString())
    }

    @Test
    fun checkErrorUi() {
        FakeRepositoryModule.shouldReturnError = true
        launchUserFragment()

        isTextVisible(R.string.unknown_error_title)
        isTextVisible(R.string.unknown_error_message)
    }

    private fun launchUserFragment() {
        hiltRule.inject()
        launchFragmentInHiltContainer<UserFragment> {}
    }
}
package com.marko.githubapp

import com.marko.githubapp.ui.commits.CommitsViewModelTest
import com.marko.githubapp.ui.repos.ReposViewModelTest
import com.marko.githubapp.ui.user.UserViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserViewModelTest::class, ReposViewModelTest::class, CommitsViewModelTest::class
)
class UnitTestSuite
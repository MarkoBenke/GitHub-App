package com.marko.githubapp

import com.marko.githubapp.ui.commits.CommitsFragmentTest
import com.marko.githubapp.ui.repos.ReposFragmentTest
import com.marko.githubapp.ui.user.UserActivityTest
import com.marko.githubapp.ui.user.UserFragmentTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserActivityTest::class,
    UserFragmentTest::class,
    ReposFragmentTest::class,
    CommitsFragmentTest::class,
)
class AndroidTestSuite
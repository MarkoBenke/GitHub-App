package com.marko.githubapp.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import shared.repository.FakeCommitsRepository
import shared.repository.FakeGitHubReposRepository
import shared.repository.FakeUserRepository
import javax.inject.Inject

abstract class BaseTest {

    @Inject
    lateinit var userRepository: FakeUserRepository

    @Inject
    lateinit var reposRepository: FakeGitHubReposRepository

    @Inject
    lateinit var commitsRepository: FakeCommitsRepository

    lateinit var context: Context

    @Before
    open fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }
}
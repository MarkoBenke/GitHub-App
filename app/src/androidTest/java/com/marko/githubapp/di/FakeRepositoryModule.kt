package com.marko.githubapp.di

import com.marko.githubapp.repository.commit.CommitsRepository
import com.marko.githubapp.repository.repos.GitHubReposRepository
import com.marko.githubapp.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shared.repository.FakeCommitsRepository
import shared.repository.FakeGitHubReposRepository
import shared.repository.FakeUserRepository
import javax.inject.Singleton

/**
 * Fake repository module class. It is used to replace RepositoryModule in tests.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeRepositoryModule {

    var shouldReturnError: Boolean = false

    @Singleton
    @Provides
    fun provideErrorBoolean(): Boolean {
        return shouldReturnError
    }

    @Singleton
    @Provides
    fun provideUserRepository(shouldReturnError: Boolean): UserRepository =
        FakeUserRepository(shouldReturnError)

    @Singleton
    @Provides
    fun provideGitHubReposRepository(shouldReturnError: Boolean): GitHubReposRepository =
        FakeGitHubReposRepository(shouldReturnError)

    @Singleton
    @Provides
    fun provideCommitsRepository(shouldReturnError: Boolean): CommitsRepository =
        FakeCommitsRepository(shouldReturnError)
}
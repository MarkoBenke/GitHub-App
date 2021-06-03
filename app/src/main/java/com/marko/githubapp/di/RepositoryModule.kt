package com.marko.githubapp.di

import com.marko.githubapp.network.mappers.CommitsMapper
import com.marko.githubapp.network.mappers.RepoMapper
import com.marko.githubapp.network.mappers.UserMapper
import com.marko.githubapp.network.service.CommitsService
import com.marko.githubapp.network.service.RepoService
import com.marko.githubapp.network.service.UserService
import com.marko.githubapp.repository.commit.CommitsRepository
import com.marko.githubapp.repository.commit.DefaultCommitsRepository
import com.marko.githubapp.repository.repos.DefaultGitHubReposRepository
import com.marko.githubapp.repository.repos.GitHubReposRepository
import com.marko.githubapp.repository.user.DefaultUserRepository
import com.marko.githubapp.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserRepository(
        userService: UserService,
        userMapper: UserMapper
    ): UserRepository = DefaultUserRepository(userService, userMapper)

    @Provides
    @ViewModelScoped
    fun provideGitHubReposRepository(
        repoService: RepoService,
        repoMapper: RepoMapper
    ): GitHubReposRepository = DefaultGitHubReposRepository(repoService, repoMapper)

    @Provides
    @ViewModelScoped
    fun provideCommitsRepository(
        commitsService: CommitsService,
        commitsMapper: CommitsMapper
    ): CommitsRepository = DefaultCommitsRepository(commitsService, commitsMapper)
}
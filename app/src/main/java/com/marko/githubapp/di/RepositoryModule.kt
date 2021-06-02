package com.marko.githubapp.di

import com.marko.githubapp.network.mappers.UserMapper
import com.marko.githubapp.network.service.UserService
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
}
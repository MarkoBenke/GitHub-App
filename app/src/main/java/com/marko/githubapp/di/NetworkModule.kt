package com.marko.githubapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marko.githubapp.network.service.CommitsService
import com.marko.githubapp.network.service.RepoService
import com.marko.githubapp.network.service.UserService
import com.marko.githubapp.network.util.NetworkUrl.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))


    @Provides
    @Singleton
    fun provideUserService(retrofitBuilder: Retrofit.Builder): UserService =
        retrofitBuilder.build().create(UserService::class.java)

    @Provides
    @Singleton
    fun provideRepoService(retrofitBuilder: Retrofit.Builder): RepoService =
        retrofitBuilder.build().create(RepoService::class.java)

    @Provides
    @Singleton
    fun provideCommitsService(retrofitBuilder: Retrofit.Builder): CommitsService =
        retrofitBuilder.build().create(CommitsService::class.java)
}
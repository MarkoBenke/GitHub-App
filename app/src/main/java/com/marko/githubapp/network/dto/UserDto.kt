package com.marko.githubapp.network.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    val name: String,
    @SerializedName("avatar_url")
    val imageUrl: String,
    val company: String?,
    val bio: String?,
    val followers: Int,
    val following: Int
)

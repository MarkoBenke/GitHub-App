package com.marko.githubapp.domain

data class User(
    val name: String,
    val imageUrl: String,
    val company: String,
    val location: String,
    val bio: String,
    val followers: Int,
    val following: Int
)

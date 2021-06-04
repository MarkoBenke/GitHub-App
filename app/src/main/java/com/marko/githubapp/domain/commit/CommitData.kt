package com.marko.githubapp.domain.commit

data class CommitData(
    val authorName: String,
    val commitDate: String,
    val message: String?,
    val commentCount: String,
    val isVerified: Boolean,
)

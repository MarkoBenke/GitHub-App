package com.marko.githubapp.domain.commit

data class Commit(
    val sha: String,
    val commitData: CommitData,
    val commitUrl: String
)

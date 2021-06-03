package com.marko.githubapp.network.dto.commit

import com.google.gson.annotations.SerializedName

data class CommitDto(
    val sha: String,
    @SerializedName("commit")
    val commitData: CommitDataDto,
    @SerializedName("html_url")
    val commitUrl: String
)
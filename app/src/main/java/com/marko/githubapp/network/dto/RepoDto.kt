package com.marko.githubapp.network.dto

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val name: String,
    val description: String,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    val language: String?
)

package com.marko.githubapp.domain

data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val openIssuesCount: String,
    val language: String?
)

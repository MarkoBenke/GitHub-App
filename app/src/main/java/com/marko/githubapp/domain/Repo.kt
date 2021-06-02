package com.marko.githubapp.domain

data class Repo(
    val name: String,
    val description: String,
    val openIssuesCount: Int,
    val language: String?
)

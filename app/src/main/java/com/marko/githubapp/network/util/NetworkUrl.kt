package com.marko.githubapp.network.util

object NetworkUrl {

    const val BASE_URL = "https://api.github.com/"
    const val GET_USER = "users/{user}"
    const val GET_USER_REPOS = "$GET_USER/repos"
    const val GET_REPO_COMMITS = "repos/{user}/{repo}/commits"
}
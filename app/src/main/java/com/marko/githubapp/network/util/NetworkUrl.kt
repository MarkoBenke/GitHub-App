package com.marko.githubapp.network.util

object NetworkUrl {

    const val BASE_URL = "https://api.github.com/"
    const val GET_USER = "users/MarkoBenke"
    const val GET_USER_REPOS = "$GET_USER/repos"
    const val GET_REPO_COMMITS = "repos/MarkoBenke/{repo}/commits"
}
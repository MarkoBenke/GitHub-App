package com.marko.githubapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val openIssuesCount: String,
    val language: String?
) : Parcelable

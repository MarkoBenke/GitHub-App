package com.marko.githubapp.network.dto.commit

import com.google.gson.annotations.SerializedName

data class CommitDataDto(
    val author: AuthorDto,
    val message: String?,
    @SerializedName("comment_count")
    val commentCount: Int,
    val verification: VerificationDto
)
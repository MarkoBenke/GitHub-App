package com.marko.githubapp.network.dto.commit

import com.google.gson.annotations.SerializedName

data class VerificationDto(
    @SerializedName("verified")
    val isVerified: Boolean,
    val reason: String?
)
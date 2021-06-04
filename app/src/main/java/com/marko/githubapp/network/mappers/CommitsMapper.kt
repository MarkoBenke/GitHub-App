package com.marko.githubapp.network.mappers

import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.domain.commit.CommitData
import com.marko.githubapp.network.dto.commit.CommitDto
import com.marko.githubapp.util.DomainMapper
import com.marko.githubapp.util.parseDate
import javax.inject.Inject

class CommitsMapper @Inject constructor() : DomainMapper<CommitDto, Commit> {

    override fun mapFromDto(dto: CommitDto) = Commit(
        sha = dto.sha,
        commitUrl = dto.commitUrl,
        commitData = CommitData(
            authorName = dto.commitData.author.name,
            commitDate = parseDate(dto.commitData.author.date),
            message = dto.commitData.message,
            commentCount = dto.commitData.commentCount.toString(),
            isVerified = dto.commitData.verification.isVerified
        )
    )

    override fun mapToDto(domain: Commit): CommitDto {
        TODO("Not yet implemented")
    }

    fun mapFromDtoList(list: List<CommitDto>) = list.map {
        mapFromDto(it)
    }
}
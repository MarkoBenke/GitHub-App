package com.marko.githubapp.network.mappers

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.network.dto.RepoDto
import com.marko.githubapp.util.DomainMapper
import javax.inject.Inject

/**
 * Class for mapping Repository models. It converts Repo object from network (RepoDto) to Domain
 * object (Repo) and vice versa.
 */
class RepoMapper @Inject constructor() : DomainMapper<RepoDto, Repo> {

    override fun mapFromDto(dto: RepoDto) = Repo(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        openIssuesCount = dto.openIssuesCount.toString(),
        language = dto.language
    )

    override fun mapToDto(domain: Repo) = RepoDto(
        id = domain.id,
        name = domain.name,
        description = domain.description,
        openIssuesCount = domain.openIssuesCount.toInt(),
        language = domain.language
    )

    fun mapFromDtoList(list: List<RepoDto>) = list.map {
        mapFromDto(it)
    }
}
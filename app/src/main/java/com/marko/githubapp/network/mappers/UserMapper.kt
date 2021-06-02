package com.marko.githubapp.network.mappers

import com.marko.githubapp.domain.User
import com.marko.githubapp.network.dto.UserDto
import com.marko.githubapp.util.DomainMapper
import javax.inject.Inject

class UserMapper @Inject constructor() : DomainMapper<UserDto, User> {

    override fun mapFromDto(dto: UserDto) = User(
        name = dto.name,
        imageUrl = dto.imageUrl,
        company = dto.company,
        location = dto.location,
        bio = dto.bio,
        followers = dto.followers,
        following = dto.following
    )

    override fun mapToDto(domain: User) = UserDto(
        name = domain.name,
        imageUrl = domain.imageUrl,
        company = domain.company,
        location = domain.location,
        bio = domain.bio,
        followers = domain.followers,
        following = domain.following
    )
}
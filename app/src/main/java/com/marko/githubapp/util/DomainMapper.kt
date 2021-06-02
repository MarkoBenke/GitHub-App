package com.marko.githubapp.util

interface DomainMapper<Dto, Domain> {

    fun mapFromDto(dto: Dto): Domain
    fun mapToDto(domain: Domain): Dto
}
package org.weframe.kotlinresourcesserver.frame

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "frame", path = "frame")
interface BackboardRepository : PagingAndSortingRepository<Frame, Long> {

    fun findByNameLike(@Param("name") name: String): Frame

}
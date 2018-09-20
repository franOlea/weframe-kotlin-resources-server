package org.weframe.kotlinresourcesserver.product.frame

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "frames", path = "frames")
interface FrameRepository : PagingAndSortingRepository<Frame, Long> {

    fun findByNameLike(@Param("name") name: String): Frame

}
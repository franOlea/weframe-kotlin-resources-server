package org.weframe.kotlinresourcesserver.product.frameglass

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "frame-glasses", path = "frame-glasses")
interface FrameGlassRepository : PagingAndSortingRepository<FrameGlass, Long> {

    fun findByNameLike(@Param("name") name: String): FrameGlass

}
package org.weframe.kotlinresourcesserver.product.backboard

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "backboards", path = "backboards")
interface BackboardRepository : PagingAndSortingRepository<Backboard, Long> {

    fun findByNameLike(@Param("name") name: String): Backboard

}
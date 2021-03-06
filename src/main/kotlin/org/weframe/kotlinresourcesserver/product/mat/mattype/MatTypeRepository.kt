package org.weframe.kotlinresourcesserver.product.mat.mattype

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "mat-types", path = "mat-types")
interface MatTypeRepository : PagingAndSortingRepository<MatType, Long> {

    fun findByNameLike(@Param("name") name: String): MatType

}
package org.weframe.kotlinresourcesserver.product.mat.mattype

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "mat-types", path = "mat-types")
interface MatTypeRepository : PagingAndSortingRepository<MatType, Long> {

    fun findByDeleted(@Param("deleted") deleted: Boolean, page: Pageable): Page<MatType>
    fun findByNameLike(@Param("name") name: String): MatType

}
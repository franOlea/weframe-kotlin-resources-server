package org.weframe.kotlinresourcesserver.backmat

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "backmats", path = "backmats")
interface BackMatRepository : PagingAndSortingRepository<BackMat, Long> {
}
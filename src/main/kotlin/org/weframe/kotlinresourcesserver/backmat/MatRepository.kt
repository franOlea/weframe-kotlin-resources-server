package org.weframe.kotlinresourcesserver.backmat

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "mats", path = "mats")
interface MatRepository : PagingAndSortingRepository<Mat, Long> {
}
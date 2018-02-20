package org.weframe.kotlinresourcesserver.product.mat

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "mats", path = "mats")
interface MatRepository : PagingAndSortingRepository<Mat, Long>
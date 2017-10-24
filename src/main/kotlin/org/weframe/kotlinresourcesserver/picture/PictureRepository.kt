package org.weframe.kotlinresourcesserver.picture

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "pictures", path = "pictures")
interface PictureRepository : PagingAndSortingRepository<Picture, Long> {

    fun findByKey(@Param("key") key: String): Picture

}
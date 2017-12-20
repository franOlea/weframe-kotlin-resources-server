package org.weframe.kotlinresourcesserver.product.picture.user

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface UserPictureRepository : PagingAndSortingRepository<UserPicture, Long> {
    fun findByUser(user: String, pageable: Pageable): List<UserPicture>
    fun countByUser(user: String): Long
}
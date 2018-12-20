package org.weframe.kotlinresourcesserver.product.picture.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface UserPictureRepository : PagingAndSortingRepository<UserPicture, Long> {
    fun findByUser(user: String, pageable: Pageable): Page<UserPicture>
}
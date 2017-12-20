package org.weframe.kotlinresourcesserver.product

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface FramedPictureRepository : PagingAndSortingRepository<FramedPicture, Long> {
    fun findByUser(user: String, pageable: Pageable): List<FramedPicture>
    fun deleteByUserAndId(user: String, id: Long)
    fun countByUser(user: String): Long
}
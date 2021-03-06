package org.weframe.kotlinresourcesserver.product.picture

import org.springframework.data.jpa.repository.JpaRepository

interface PictureRepository : JpaRepository<Picture, Long> {

    fun findByKey(key: String) : Picture
    fun deleteByKey(key: String)

}
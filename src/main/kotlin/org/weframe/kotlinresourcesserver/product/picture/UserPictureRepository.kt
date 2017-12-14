package org.weframe.kotlinresourcesserver.product.picture

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@Suppress("unused")
@RepositoryRestResource(collectionResourceRel = "user-pictures", path = "user-pictures")
interface UserPictureRepository : Repository<Picture, Long> {

    @Query("select userPicture from UserPicture as userPicture where userPicture.picture.key = ?1 and userPicture.user = ?#{principal?.name}")
    fun findByKeyAndUser(@Param("key") key: String): UserPicture


    @Query("select userPicture from UserPicture as userPicture where userPicture.user = ?#{principal?.name}")
    fun findByUser(): UserPicture

}
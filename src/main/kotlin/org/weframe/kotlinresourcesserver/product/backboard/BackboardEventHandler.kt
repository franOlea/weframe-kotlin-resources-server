package org.weframe.kotlinresourcesserver.product.backboard

import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository

@Component
@RepositoryEventHandler(Backboard::class)
class BackboardEventHandler(private val pictureRepository: PictureRepository) {

    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(backboard: Backboard) {
        val picture = pictureRepository.findByKey(backboard.picture!!.key!!)
        backboard.picture = picture
    }

}
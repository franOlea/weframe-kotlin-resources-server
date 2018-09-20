package org.weframe.kotlinresourcesserver.product.frame

import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository

@Component
@RepositoryEventHandler(Frame::class)
class FrameEventHandler(private val pictureRepository: PictureRepository) {

    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(frame: Frame) {
        val picture = pictureRepository.findByKey(frame.picture!!.key!!)
        frame.picture = picture
    }

}
package org.weframe.kotlinresourcesserver.product

import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository

@Component
@RepositoryEventHandler
class FrameEventHandler(private val pictureRepository: PictureRepository) {

    @HandleBeforeCreate(Frame::class)
    fun handleFrameSave(frame: Frame) {
        val picture = pictureRepository.findByKey(frame.picture!!.key!!)
        frame.picture = picture
    }

}

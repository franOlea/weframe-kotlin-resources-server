package org.weframe.kotlinresourcesserver.product.mat.mattype

import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository

@Component
@RepositoryEventHandler(MatType::class)
class MatTypeEventHandler(private val pictureRepository: PictureRepository) {

    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(matType: MatType) {
        val picture = pictureRepository.findByKey(matType.picture!!.key!!)
        matType.picture = picture
    }

}
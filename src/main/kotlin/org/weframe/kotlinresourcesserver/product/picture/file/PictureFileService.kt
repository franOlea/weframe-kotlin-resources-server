package org.weframe.kotlinresourcesserver.product.picture.file

import java.awt.image.BufferedImage

interface PictureFileService {

    fun loadPictureByKey(key: String): BufferedImage?

    fun generatePictureUrl(key: String, thumbnail: Boolean): String?

    fun savePicture(bufferedImage: BufferedImage, key: String, formatName: String)

    fun deletePicture(key: String)

}
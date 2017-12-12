package org.weframe.kotlinresourcesserver.picture.file

import java.awt.image.BufferedImage



interface PictureFileService {

    fun loadPictureByKey(key: String): Pair<BufferedImage, String>?

    fun generatePictureUrl(key: String): String?

    fun savePicture(bufferedImage: BufferedImage, key: String, formatName: String)

    fun deletePicture(key: String)

}
package org.weframe.kotlinresourcesserver.picture.file

import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

open class MultipartFileReader {
    fun read(multipartFile: MultipartFile): BufferedImage {
        return ImageIO.read(multipartFile.inputStream)
    }
}
package org.weframe.kotlinresourcesserver.product.picture.file

import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

open class MultipartFileReader {
    open fun read(multipartFile: MultipartFile): BufferedImage {
        return ImageIO.read(multipartFile.inputStream)
    }
}
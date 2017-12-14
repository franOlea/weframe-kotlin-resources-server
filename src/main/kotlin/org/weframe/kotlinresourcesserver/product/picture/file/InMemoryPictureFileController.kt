package org.weframe.kotlinresourcesserver.product.picture.file

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/pictures")
class InMemoryPictureFileController(val fileService: PictureFileService) {

    @RequestMapping(value = "/in-memory/{pictureId}", method = arrayOf(RequestMethod.GET))
    private fun getPictureImage(@PathVariable("pictureId") pictureId: String, response: HttpServletResponse) {
        val image = fileService.loadPictureByKey(pictureId)
        if (image != null) {
            val jpegOutputStream = ByteArrayOutputStream()
            ImageIO.write(image.first, image.second, jpegOutputStream)
            val imgByte = jpegOutputStream.toByteArray()
            preparePictureResponse(response, imgByte, image.second)
        } else {
            prepareEmptyResponse(response)
        }
    }

    private fun preparePictureResponse(response: HttpServletResponse, imgByte: ByteArray, formatName: String) {
        response.setHeader("Cache-Control", "no-store")
        response.setHeader("Pragma", "no-cache")
        response.setDateHeader("Expires", 0)
        response.contentType = "image/" + formatName
        val responseOutputStream = response.outputStream
        responseOutputStream.write(imgByte)
        responseOutputStream.flush()
        responseOutputStream.close()
    }

    private fun prepareEmptyResponse(response: HttpServletResponse) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND)
    }

}
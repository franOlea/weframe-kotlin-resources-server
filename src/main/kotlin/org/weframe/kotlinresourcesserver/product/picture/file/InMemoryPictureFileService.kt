package org.weframe.kotlinresourcesserver.product.picture.file

import java.awt.image.BufferedImage

class InMemoryPictureFileService(serverIp: String, serverPort: String) : PictureFileService {

    private val serverAddress: String = "http://$serverIp:$serverPort"
    private val picturesMap: MutableMap<String, Pair<BufferedImage, String>> = HashMap()

    override fun loadPictureByKey(key: String): Pair<BufferedImage, String>? {
        return picturesMap[key]
    }

    override fun generatePictureUrl(key: String, thumbnail: Boolean): String? {
        return if(picturesMap.containsKey(key)) {
            "$serverAddress/pictures/in-memory/$key${if(thumbnail) "-thumbnail" else ""}"
        } else {
            "$serverAddress/pictures/error"
        }
    }

    override fun savePicture(bufferedImage: BufferedImage, key: String, formatName: String) {
        picturesMap[key] = Pair(bufferedImage, formatName)
        picturesMap["$key-thumbnail"] = Pair(bufferedImage, formatName)
    }

    override fun deletePicture(key: String) {
        picturesMap.remove(key)
    }
}
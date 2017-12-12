package org.weframe.kotlinresourcesserver.picture.file

import java.awt.image.BufferedImage

class PictureFileInMemoryService(serverIp: String, serverPort: String) : PictureFileService {

    private val serverAddress: String = "$serverIp:$serverPort"
    private val picturesMap: MutableMap<String, Pair<BufferedImage, String>> = HashMap()

    override fun loadPictureByKey(key: String): Pair<BufferedImage, String>? {
        return picturesMap[key]
    }

    override fun generatePictureUrl(key: String): String? {
        return if(picturesMap.containsKey(key)) {
            "$serverAddress/pictures/in-memory/$key"
        } else {
            "$serverAddress/pictures/error"
        }
    }

    override fun savePicture(bufferedImage: BufferedImage, key: String, formatName: String) {
        picturesMap.put(key, Pair(bufferedImage, formatName))
    }

    override fun deletePicture(key: String) {
        picturesMap.remove(key)
    }
}
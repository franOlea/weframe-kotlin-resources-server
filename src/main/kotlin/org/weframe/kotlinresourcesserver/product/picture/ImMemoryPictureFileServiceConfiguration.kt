package org.weframe.kotlinresourcesserver.product.picture

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.weframe.kotlinresourcesserver.product.picture.file.InMemoryPictureFileService
import org.weframe.kotlinresourcesserver.product.picture.file.MultipartFileReader
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService

@Profile("local")
@Configuration
class ImMemoryPictureFileServiceConfiguration {

    @Value("\${service.address}")
    private val serverAddress: String? = null

    @Value("\${service.port}")
    private val serverPort: String? = null

    @Bean
    fun getPictureFileService() : PictureFileService {
        return if(serverPort != null) {
            InMemoryPictureFileService("$serverAddress:$serverPort")
        } else {
            InMemoryPictureFileService(serverAddress!!)
        }
    }

    @Bean
    fun getMultipartFileReader() : MultipartFileReader {
        return MultipartFileReader()
    }

}
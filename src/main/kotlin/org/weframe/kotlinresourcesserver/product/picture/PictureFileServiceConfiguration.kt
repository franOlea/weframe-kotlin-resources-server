package org.weframe.kotlinresourcesserver.product.picture

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weframe.kotlinresourcesserver.product.picture.file.InMemoryPictureFileService
import org.weframe.kotlinresourcesserver.product.picture.file.MultipartFileReader
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService

@Configuration
class PictureFileServiceConfiguration {

    @Value("\${server.address}")
    private val serverAddress: String? = null

    @Value("\${server.port}")
    private val serverPort: String? = null

    @Bean
    fun getPictureFileService() : PictureFileService {
        return InMemoryPictureFileService(serverAddress!!, serverPort!!)
    }

    @Bean
    fun getMultipartFileReader() : MultipartFileReader {
        return MultipartFileReader()
    }

}
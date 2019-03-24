package org.weframe.kotlinresourcesserver.product.picture

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.weframe.kotlinresourcesserver.product.picture.file.MultipartFileReader
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import org.weframe.kotlinresourcesserver.product.picture.file.S3PictureFileService


@Profile("aws")
@Configuration
class S3PictureFileServiceConfiguration {

    @Value("\${image.storage.key}")
    private val keyId: String? = null

    @Value("\${image.storage.secret}")
    private val keySecret: String? = null

    @Value("\${image.storage.bucket.name}")
    private val bucketName: String? = null

    @Bean
    fun getPictureFileService() : PictureFileService {
        val clientConfiguration = ClientConfiguration()
        val credentials = BasicAWSCredentials(keyId, keySecret)
        val credentialsProvider = AWSStaticCredentialsProvider(credentials)

        val amazonS3Client = AmazonS3Client.builder()
                .withClientConfiguration(clientConfiguration)
                .withCredentials(credentialsProvider)
                .withRegion(Regions.SA_EAST_1)
                .build() as AmazonS3Client
        return S3PictureFileService(amazonS3Client, bucketName!!)
    }

    @Bean
    fun getMultipartFileReader() : MultipartFileReader {
        return MultipartFileReader()
    }
}
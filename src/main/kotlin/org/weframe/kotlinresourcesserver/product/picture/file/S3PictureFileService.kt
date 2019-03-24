package org.weframe.kotlinresourcesserver.product.picture.file

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.time.Instant
import java.util.*
import javax.imageio.ImageIO
import com.amazonaws.services.s3.model.PutObjectRequest
import java.io.ByteArrayInputStream




class S3PictureFileService(private val amazonS3Client: AmazonS3Client, private val bucketName: String) : PictureFileService {

    override fun loadPictureByKey(key: String): BufferedImage {
        val objectInputStream = amazonS3Client.getObject(bucketName, key).objectContent
        return ImageIO.read(objectInputStream)
    }

    override fun generatePictureUrl(key: String, thumbnail: Boolean): String? {
        val expiration = Instant.now()
        val expirationTime = (1000 * 60 * 60).toLong() // 1 hour.
        val generatePresignedUrlRequest = GeneratePresignedUrlRequest(bucketName, key)
        val linkExpirationDate = Date(expiration.plusMillis(expirationTime).toEpochMilli())
        generatePresignedUrlRequest.method = HttpMethod.GET // Default.
        generatePresignedUrlRequest.expiration = linkExpirationDate
        val url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest)
        return url.toString()
    }

    override fun savePicture(bufferedImage: BufferedImage, key: String, formatName: String) {
        val os = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, formatName, os)
        val buffer = os.toByteArray()
        val inputStream = ByteArrayInputStream(buffer)
        val meta = ObjectMetadata()
        meta.contentLength = buffer.size.toLong()
        meta.contentType = "image/$formatName"
        amazonS3Client.putObject(PutObjectRequest(bucketName, key, inputStream, meta))
    }

    override fun deletePicture(key: String) {
        amazonS3Client.deleteObject(bucketName, key)
    }
}
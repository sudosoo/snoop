package com.api.pladder.core.utils.s3

import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.core.utils.file.FileStorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.ResponseBytes
import software.amazon.awssdk.core.exception.SdkException
import software.amazon.awssdk.core.sync.RequestBody.fromBytes
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.NoSuchKeyException
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.IOException

@Component
class ImageS3Provider (
    private val s3Client: S3Client,
) :FileStorageService {
    @Value("\${cloud.aws.s3.bucket}")
    private val bucketName: String? = null
    override fun upload(fileName: String, file: MultipartFile) {
        try {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("$fileName")
                .contentType(file.contentType)
                .build()
            val body = fromBytes(file.bytes)
            s3Client.putObject(putObjectRequest,body)
        } catch (e: IOException) {
            // 처리 중 발생한 예외 처리
            e.printStackTrace()
            throw e
        }
    }

    override fun download(fileName: String): ByteArray {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build()
        try {
            val responseBytes: ResponseBytes<*> = s3Client.getObjectAsBytes(getObjectRequest)
            return responseBytes.asByteArray()
        } catch (e: NoSuchKeyException) {
            // S3에서 이미지를 찾을 수 없는 경우
            throw NotFoundException("File not found in S3: $fileName")
        } catch (e: IOException) {
            // 처리 중 발생한 예외 처리
            e.printStackTrace()
            throw e
        }
    }

    override fun delete(fileName: String) {
        val deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build()

        try {
            s3Client.deleteObject(deleteObjectRequest)
        } catch (e: SdkException) {
            // S3에서 이미지를 삭제할 수 없는 경우
            e.printStackTrace()
            throw IOException("Error deleting file from S3: $fileName")
        }
    }
}
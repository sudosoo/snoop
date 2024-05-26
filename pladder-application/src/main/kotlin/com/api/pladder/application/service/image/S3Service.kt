package com.api.pladder.application.service.image

import com.api.pladder.application.core.exception.NotFoundException
import com.api.pladder.application.dto.image.request.ImageReq
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import software.amazon.awssdk.core.ResponseBytes
import software.amazon.awssdk.core.sync.RequestBody.fromBytes
import software.amazon.awssdk.core.exception.SdkException
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.NoSuchKeyException
import software.amazon.awssdk.services.s3.model.PutObjectRequest


import java.io.IOException

@Service
class S3Service (
    private val s3Client : S3Client
) : ImgStorageService {

    @Value("\${cloud.bucket.name}")
    private val bucketName: String? = null
    override fun uploadImage(fileName: String, imageReq: ImageReq) {
        try {
            val file = imageReq.file
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

    override fun downloadImage(fileName: String): ByteArray {
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

    override fun deleteImage(fileName: String) {
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
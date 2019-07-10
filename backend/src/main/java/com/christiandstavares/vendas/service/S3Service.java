package com.christiandstavares.vendas.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.christiandstavares.vendas.exception.ArquivoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private static Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    @Value("${s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            InputStream inputStream = multipartFile.getInputStream();
            return uploadFile(inputStream, fileName, contentType);
        } catch (IOException e) {
            throw new ArquivoException("Erro de IO: " + e.getMessage());
        }

        /*try {
            File file = new File(localFilePath);
            LOGGER.info("Iniciando upload...");
            amazonS3.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            LOGGER.info("Upload finalizado!");
        } catch (AmazonServiceException e) {
            LOGGER.info("AmazonServiceException: " + e.getMessage());
            LOGGER.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOGGER.info("AmazonClientException: " + e.getMessage());
        }*/
    }

    private URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            LOGGER.info("Iniciando upload...");
            amazonS3.putObject(bucketName, fileName, inputStream, metadata);
            LOGGER.info("Upload finalizado!");
            return amazonS3.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new ArquivoException("Erro ao converter URL para URI");
        }
    }
}

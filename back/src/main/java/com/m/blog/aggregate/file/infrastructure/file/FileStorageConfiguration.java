package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.m.blog.global.properties.AwsProperties;
import com.m.blog.global.properties.FileProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfiguration {
    private final FileProperties fileProperties;
    private final boolean isLocal;
    private final AwsProperties awsProperties;
    private final AmazonS3Client amazonS3Client;
    private final AmazonS3 amazonS3;

    public FileStorageConfiguration(FileProperties fileProperties, AwsProperties awsProperties, AmazonS3Client amazonS3Client, AmazonS3 amazonS3) {
        this.fileProperties = fileProperties;
        this.isLocal = fileProperties.isForLocal();

        this.awsProperties = awsProperties;
        this.amazonS3Client = amazonS3Client;
        this.amazonS3 = amazonS3;
    }

    @Bean
    FileUploadUtil fileUploadUtil(){
        return isLocal? new LocalFileUploadUtilImpl() :
                new S3FileUploadUtilImpl(awsProperties, amazonS3Client);
    }

    @Bean
    FileDownloadUtil fileDownloadUtil(){
        return isLocal? new LocalFileDownloadUtilImpl():
            new S3FileDownloadUtilImpl(amazonS3, awsProperties);
    }
}

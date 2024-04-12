package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
class FileDownloadUtilImpl implements FileDownloadUtil {
    private final AmazonS3 amazonS3;
    private final AwsProperties awsProperties;

    @Override
    public InputStream getS3Resource(File file){
        S3Object s3Object = amazonS3.getObject(
                new GetObjectRequest(awsProperties.getS3().getBucket(), file.getFileKey())
        );

        return s3Object.getObjectContent();
    }

    @Override
    public InputStream getLocalResource(File file) throws IOException {
        Path path = Paths.get(file.getFileKey());

        return Files.newInputStream(path);
    }
}

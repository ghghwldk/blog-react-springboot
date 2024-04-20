package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.m.blog.aggregate.file.application.domain.BlogFile;
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
    public InputStream getS3Resource(BlogFile blogFile){
        S3Object s3Object = amazonS3.getObject(
                new GetObjectRequest(awsProperties.getS3().getBucket(), blogFile.getFileKey())
        );

        return s3Object.getObjectContent();
    }

    @Override
    public InputStream getLocalResource(BlogFile blogFile) throws IOException {
        Path path = Paths.get(blogFile.getFileKey());

        return Files.newInputStream(path);
    }
}

package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.global.properties.FileProperties;
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
    private final FileProperties fileProperties;

    @Override
    public InputStream getS3Resource(BaseFile baseFile){
        S3Object s3Object = amazonS3.getObject(
                new GetObjectRequest(fileProperties.getBucket(), baseFile.getFileKey())
        );

        return s3Object.getObjectContent();
    }

    @Override
    public InputStream getLocalResource(BaseFile baseFile) throws IOException {
        Path path = Paths.get(baseFile.getFileKey());

        return Files.newInputStream(path);
    }
}

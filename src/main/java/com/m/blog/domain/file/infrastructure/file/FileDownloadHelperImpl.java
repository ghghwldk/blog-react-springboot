package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.m.blog.domain.file.application.domain.DownloadFile;
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
class FileDownloadHelperImpl implements FileDownloadHelper {
    private final AmazonS3 amazonS3;
    private final FileProperties fileProperties;

    @Override
    public InputStream getS3Resource(DownloadFile downloadFile){
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(fileProperties.getBucket(), downloadFile.getKey()));

        return s3Object.getObjectContent();
    }

    @Override
    public InputStream getLocalResource(DownloadFile downloadFile) throws IOException {
        Path path = Paths.get(downloadFile.getKey());

        return Files.newInputStream(path);
    }
}

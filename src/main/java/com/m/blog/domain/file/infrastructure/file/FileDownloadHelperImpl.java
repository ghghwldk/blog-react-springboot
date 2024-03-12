package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.m.blog.domain.file.application.domain.DownloadFileInfo;
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
    public InputStream getS3Resource(DownloadFileInfo downloadFileInfo){
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(fileProperties.getBucket(), downloadFileInfo.getKey()));

        return s3Object.getObjectContent();
    }

    @Override
    public InputStream getLocalResource(DownloadFileInfo downloadFileInfo) throws IOException {
        Path path = Paths.get(downloadFileInfo.getKey());

        return Files.newInputStream(path);
    }
}

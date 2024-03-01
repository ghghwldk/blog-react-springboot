package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.m.blog.domain.file.domain.DownloadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
class FileDownloadService implements FileDownload {
    private final AmazonS3 amazonS3;
    @Value("${aws.s3.bucket:#{null}}")
    private String bucket;

    @Override
    public Resource getS3Resource(DownloadFile fileVo){
        S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, fileVo.getKey()));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        Resource resource = new InputStreamResource(objectInputStream);

        return resource;
    }

    @Override
    public Resource getLocalResource(DownloadFile fileVo) throws IOException {
        Path path = Paths.get(fileVo.getKey());

        return new InputStreamResource(Files.newInputStream(path));
    }
}

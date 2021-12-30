package com.m.blog.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.m.blog.entity.File;
import com.m.blog.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    @Autowired
    FileJpaRepository fileJpaRepository;

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public ResponseEntity<Resource> getObject(String fileName) throws IOException {
        File file = fileJpaRepository.findByFileName(fileName);
        String path= file.getFilePath();
        String key= path+ "/" + fileName;
        String originalName= file.getOriginalFileName();

        S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, key));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        Resource resource = new InputStreamResource(objectInputStream);
        String encoded = URLEncoder.encode(originalName, "UTF-8").replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encoded + "\"")
                .body(resource);
    }
}

package com.m.blog.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.m.blog.domain.file.dto.FileDownloadRequestDto;
import com.m.blog.domain.file.entity.File;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.vo.DownloadFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService{
    @Autowired
    FileJpaRepository fileJpaRepository;

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;


    private Resource getResource(DownloadFileVo fileVo){
        S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, fileVo.getKey()));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        Resource resource = new InputStreamResource(objectInputStream);

        return resource;
    }

    private String getHeaderValues(DownloadFileVo fileVo) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(fileVo.getOriginalName(), "UTF-8").replaceAll("\\+", "%20");
        return "attachment; filename=\"" + encoded + "\"";
    }

    @Override
    public ResponseEntity<Resource> get(FileDownloadRequestDto requestDto) throws UnsupportedEncodingException {
        File file = fileJpaRepository.findByFileName(requestDto.getFileName())
                .orElseThrow(RuntimeException::new);

        DownloadFileVo fileVo = DownloadFileVo.of(file, requestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, getHeaderValues(fileVo))
                .body(this.getResource(fileVo));
    }
}

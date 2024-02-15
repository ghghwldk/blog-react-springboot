package com.m.blog.domain.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.domain.file.dto.FileUploadRequestDto;
import com.m.blog.domain.file.dto.FileUploadResponseDto;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.util.FileUtil;
import com.m.blog.domain.file.vo.FileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService{
    private final FileJpaRepository fileJpaRepository;
    private final AmazonS3Client amazonS3Client;
    private final FileUtil fileUtil;

    @Value("${file.directory}") private String directoryName; // static
    @Value("${aws.s3.bucket}") private String bucket;

    @Override
    public FileUploadResponseDto upload(FileUploadRequestDto requestDto) throws IOException{
        FileVo fileVo = FileVo.of(requestDto.getMultipartFile());

        this.upload(fileVo);

        fileJpaRepository.save(com.m.blog.domain.file.entity.File.of(fileVo, this.directoryName));
        return FileUploadResponseDto.of(fileVo);
    }

    private String upload(FileVo fileVo) throws IOException {
        File converted = fileUtil.convert(fileVo.getMultipartFile())
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        String uploadImageUrl = putS3(converted, fileVo);

        fileUtil.removeNewFile(converted);

        return uploadImageUrl;
    }

    private String putS3(File uploadFile, FileVo fileVo) {
        String key= this.directoryName + "/" + fileVo.getSavedFileName();

        amazonS3Client.putObject(new PutObjectRequest(bucket, key, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, key).toString();
    }
}

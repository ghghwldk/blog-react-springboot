package com.m.blog.domain.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.domain.file.dto.FileUploadRequestDto;
import com.m.blog.domain.file.dto.FileUploadResponseDto;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.util.FileUploadUtil;
import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService{
    private final FileJpaRepository fileJpaRepository;
    private final FileUploadUtil fileUploadUtil;

    @Value("${file.directory}") private String directoryName; // static
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal}")
    private boolean isLocal;

    @Override
    public FileUploadResponseDto upload(FileUploadRequestDto requestDto) throws IOException{
        UploadFileVo fileVo = UploadFileVo.of(requestDto.getMultipartFile());

        this.upload(fileVo);

        fileJpaRepository.save(com.m.blog.domain.file.entity.File.of(fileVo, this.directoryName));
        return FileUploadResponseDto.of(fileVo);
    }

    private void upload(UploadFileVo fileVo) throws IOException {
        if(isLocal){
            fileUploadUtil.uploadOnLocal(fileVo);
        }else{
            fileUploadUtil.uploadOnS3(fileVo);
        }
    }
}

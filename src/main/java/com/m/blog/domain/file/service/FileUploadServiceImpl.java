package com.m.blog.domain.file.service;

import com.m.blog.domain.file.adapter.entrypoint.api.FileUploadPort;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.infrastructure.file.FileUpload;
import com.m.blog.domain.file.infrastructure.file.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadPort {
    private final FileJpaRepository fileJpaRepository;
    private final FileUpload fileUploadUtil;

    @Value("${file.directory}") private String directoryName; // static
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal}")
    private boolean isLocal;

    @Override
    public FileUploadResponse upload(FileUploadRequest requestDto) throws IOException{
        UploadFileVo fileVo = UploadFileVo.of(requestDto.getMultipartFile());

        this.upload(fileVo);

        fileJpaRepository.save(FileEntity.of(fileVo, this.directoryName));
        return FileUploadResponse.of(fileVo);
    }

    private void upload(UploadFileVo fileVo) throws IOException {
        if(isLocal){
            fileUploadUtil.uploadOnLocal(fileVo);
        }else{
            fileUploadUtil.uploadOnS3(fileVo);
        }
    }
}

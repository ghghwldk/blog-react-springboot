package com.m.blog.domain.file.service;

import com.m.blog.domain.file.dto.FileUploadRequest;
import com.m.blog.domain.file.dto.FileUploadResponse;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.util.FileUploadUtil;
import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public FileUploadResponse upload(FileUploadRequest requestDto) throws IOException{
        UploadFileVo fileVo = UploadFileVo.of(requestDto.getMultipartFile());

        this.upload(fileVo);

        fileJpaRepository.save(com.m.blog.domain.file.entity.File.of(fileVo, this.directoryName));
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

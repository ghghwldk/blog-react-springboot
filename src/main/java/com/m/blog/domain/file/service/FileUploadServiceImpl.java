package com.m.blog.domain.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.domain.file.dto.FileUploadRequestDto;
import com.m.blog.domain.file.dto.FileUploadResponseDto;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.util.FileUtil;
import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService{
    private final FileJpaRepository fileJpaRepository;
    private final AmazonS3Client amazonS3Client;
    private final FileUtil fileUtil;

    @Value("${file.directory}") private String directoryName; // static
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal}") private boolean isLocal;

    @Override
    public FileUploadResponseDto upload(FileUploadRequestDto requestDto) throws IOException{
        UploadFileVo fileVo = UploadFileVo.of(requestDto.getMultipartFile());

        this.upload(fileVo);

        fileJpaRepository.save(com.m.blog.domain.file.entity.File.of(fileVo, this.directoryName));
        return FileUploadResponseDto.of(fileVo);
    }

    private void upload(UploadFileVo fileVo) throws IOException {
        File file = null;

        if(isLocal){
            log.info("file is uploading on the local pc");
            try{
                InputStream fileStream = fileVo.getMultipartFile().getInputStream();
                file = new File(this.directoryName + fileVo.getSavedFileName());
                FileUtils.copyInputStreamToFile(fileStream, file);
            }catch (IOException e) {
                FileUtils.deleteQuietly(file);
                throw e;
            }
        }else{
            try{
                file = fileUtil.convert(fileVo.getMultipartFile())
                        .orElseThrow(() ->
                                new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")
                        );

                putS3(file, fileVo);
            }catch (Exception e){
                throw e;
            }finally{
                fileUtil.removeNewFile(file);
            }
        }
    }

    private void putS3(File uploadFile, UploadFileVo fileVo) {
        String key= this.directoryName + "/" + fileVo.getSavedFileName();

        amazonS3Client
                .putObject(new PutObjectRequest(bucket, key, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}

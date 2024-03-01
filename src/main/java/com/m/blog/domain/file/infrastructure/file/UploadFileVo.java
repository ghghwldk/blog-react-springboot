package com.m.blog.domain.file.infrastructure.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UploadFileVo {
    private String originalFileName;
    private String extension;
    private String savedFileName;
    private MultipartFile multipartFile;

    public static UploadFileVo of(MultipartFile multipartFile){
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return UploadFileVo.builder()
                .originalFileName(originalFileName)
                .extension(extension)
                .savedFileName(UUID.randomUUID() + extension)
                .multipartFile(multipartFile)
                .build();
    }
}


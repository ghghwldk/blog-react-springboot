package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UploadFile {
    private String originalFileName;
    private String extension;
    private String savedFileName;
    private MultipartFile multipartFile;

    public static UploadFile of(MultipartFile multipartFile){
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return UploadFile.builder()
                .originalFileName(originalFileName)
                .extension(extension)
                .savedFileName(UUID.randomUUID() + extension)
                .multipartFile(multipartFile)
                .build();
    }
}


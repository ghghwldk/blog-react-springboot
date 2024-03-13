package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UploadFile {
    private String originalFileName;
    private String extension;
    private String savedFileName;
    private String directoryName;
    private byte[] data;

    public String getDownloadUrl(){
        return "/file/download/"+ savedFileName;
    }

    public static UploadFile of(MultipartFile multipartFile, String directoryName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return UploadFile.builder()
                .originalFileName(originalFileName)
                .extension(extension)
                .savedFileName(UUID.randomUUID() + extension)
                .directoryName(directoryName)
                .data(multipartFile.getBytes())
                .build();
    }

    public String getS3Key(String directoryName){
        return directoryName + "/" + savedFileName;
    }
}


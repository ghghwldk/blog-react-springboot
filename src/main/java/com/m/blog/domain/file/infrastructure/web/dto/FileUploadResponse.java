package com.m.blog.domain.file.infrastructure.web.dto;

import com.m.blog.domain.file.domain.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponse {
    private String originalFileName;
    private String fileName;
    private String url;
    private String downloadUrl;

    public static FileUploadResponse of(UploadFile vo){
        final String url = "/file/download/"+ vo.getSavedFileName();

        return FileUploadResponse.builder()
                .originalFileName(vo.getOriginalFileName())
                .fileName(vo.getSavedFileName())
                .url(url)
                .downloadUrl(url)
                .build();
    }


}
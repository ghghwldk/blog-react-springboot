package com.m.blog.domain.file.infrastructure.web.dto;

import com.m.blog.domain.file.application.domain.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponse {
    private String originalFileName;
    private String fileName;
    private String downloadUrl;

    public static FileUploadResponse of(UploadFile uploadFile){
        final String url = uploadFile.getDownloadUrl();

        return FileUploadResponse.builder()
                .originalFileName(uploadFile.getOriginalFileName())
                .fileName(uploadFile.getAssignedFileName())
                .downloadUrl(url)
                .build();
    }


}
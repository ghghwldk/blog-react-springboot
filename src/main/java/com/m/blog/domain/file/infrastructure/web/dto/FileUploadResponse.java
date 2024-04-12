package com.m.blog.domain.file.infrastructure.web.dto;

import com.m.blog.domain.file.application.domain.UploadedFile;
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

    public static FileUploadResponse of(UploadedFile uploadedFile){
        final String url = uploadedFile.getDownloadUrl();

        return FileUploadResponse.builder()
                .originalFileName(uploadedFile.getOriginalFileName())
                .fileName(uploadedFile.getFileId().getValue())
                .downloadUrl(url)
                .build();
    }


}
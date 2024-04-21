package com.m.blog.aggregate.file.infrastructure.web.dto;

import com.m.blog.aggregate.file.application.domain.BlogFile;
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

    public static FileUploadResponse of(BlogFile blogFile){
        final String url = blogFile.getDownloadUrl();

        return FileUploadResponse.builder()
                .originalFileName(blogFile.getOriginalFileName())
                .fileName(blogFile.getFileId().getValue())
                .downloadUrl(url)
                .build();
    }


}
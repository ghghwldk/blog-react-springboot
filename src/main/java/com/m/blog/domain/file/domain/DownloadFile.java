package com.m.blog.domain.file.domain;

import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DownloadFile {
    private String path;
    private String key;
    private String originalName;

    public static DownloadFile of(File file, FileDownloadRequest requestDto){
        return DownloadFile.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + requestDto.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }
}


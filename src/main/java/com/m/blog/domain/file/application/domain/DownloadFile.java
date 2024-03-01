package com.m.blog.domain.file.application.domain;

import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

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


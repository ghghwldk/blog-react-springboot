package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
@AllArgsConstructor
public class DownloadResult {
    InputStream data;
    DownloadFile downloadFile;

    public static DownloadResult get(InputStream data, DownloadFile downloadFile){
        return DownloadResult.builder()
                .data(data)
                .downloadFile(downloadFile)
                .build();
    }
}

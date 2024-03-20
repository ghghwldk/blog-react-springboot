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
    File file;

    public static DownloadResult from(InputStream data, File file){
        return DownloadResult.builder()
                .data(data)
                .file(file)
                .build();
    }
}

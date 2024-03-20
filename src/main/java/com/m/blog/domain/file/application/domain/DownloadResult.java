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
    BaseFile baseFile;

    public static DownloadResult from(InputStream data, BaseFile baseFile){
        return DownloadResult.builder()
                .data(data)
                .baseFile(baseFile)
                .build();
    }
}

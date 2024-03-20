package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Getter
@SuperBuilder
@AllArgsConstructor
public class DownloadedFile extends BaseFile{
    InputStream data;

    public static DownloadedFile from(InputStream data, File file){
        return DownloadedFile.builder()
                .assignedFileName(file.getAssignedFileName())
                .originalFileName(file.getOriginalFileName())
                .directoryName(file.getDirectoryName())
                .data(data)
                .build();
    }
}

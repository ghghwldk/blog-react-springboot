package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Getter
@SuperBuilder
@AllArgsConstructor
public class DownloadedFile extends BaseFile{
    byte[] data;

    public static DownloadedFile from(byte[] data, File file){
        return DownloadedFile.builder()
                .assignedFileName(file.getAssignedFileName())
                .originalFileName(file.getOriginalFileName())
                .directoryName(file.getDirectoryName())
                .data(data)
                .build();
    }
}

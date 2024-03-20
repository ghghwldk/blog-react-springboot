package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.io.InputStream;

@Getter
@SuperBuilder
@AllArgsConstructor
public class DownloadResult extends BaseFile{
    InputStream data;

    public static DownloadResult from(InputStream data, File file){
        return DownloadResult.builder()
                .assignedFileName(file.getAssignedFileName())
                .originalFileName(file.getOriginalFileName())
                .directoryName(file.getDirectoryName())
                .data(data)
                .build();
    }
}

package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;

import static com.m.blog.aggregate.file.application.domain.File_.withoutId;

@Mapper
class FileEntrypointMapper {
    static FileUploadResponse toUploadResponse(File_ file){
        return FileUploadResponse.builder()
                .originalFileName(file.getOriginalFileName())
                .fileName(file.getFileId().getValue())
                .downloadUrl(file.getDownloadUrl())
                .build();
    }


}

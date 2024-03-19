package com.m.blog.domain.file.infrastructure.repository;

import com.m.blog.domain.file.application.domain.UploadFile;

public class FilePersistenceMapper {
    public static FileEntity of(UploadFile vo){
        return FileEntity.builder()
                .fileName(vo.getSavedFileName())
                .originalFileName(vo.getOriginalFileName())
                .filePath(vo.getDirectoryName())
                .build();
    }
}

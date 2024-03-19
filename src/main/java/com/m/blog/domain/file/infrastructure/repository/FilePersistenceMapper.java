package com.m.blog.domain.file.infrastructure.repository;

import com.m.blog.domain.file.application.domain.UploadFile;

public class FilePersistenceMapper {
    public static FileEntity of(UploadFile uploadFile){
        return FileEntity.builder()
                .assignedFileName(uploadFile.getAssignedFileName())
                .originalFileName(uploadFile.getOriginalFileName())
                .filePath(uploadFile.getDirectoryName())
                .build();
    }
}

package com.m.blog.domain.file.infrastructure.repository;

import com.m.blog.domain.file.application.domain.UploadedFile;

public class FilePersistenceMapper {
    public static FileEntity of(UploadedFile uploadedFile){
        return FileEntity.builder()
                .assignedFileName(uploadedFile.getAssignedFileName())
                .originalFileName(uploadedFile.getOriginalFileName())
                .filePath(uploadedFile.getDirectoryName())
                .build();
    }
}

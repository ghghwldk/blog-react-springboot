package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;

public class FileJpaMapper {
    public static BaseFile toDomain(FileEntity fileEntity){
        return BaseFile.builder()
                .assignedFileName(fileEntity.getAssignedFileName())
                .originalFileName(fileEntity.getOriginalFileName())
                .directoryName(fileEntity.getFilePath())
                .build();
    }

    public static FileEntity toEntity(BaseFile baseFile){
        return FileEntity.builder()
                .assignedFileName(baseFile.getAssignedFileName())
                .originalFileName(baseFile.getOriginalFileName())
                .filePath(baseFile.getDirectoryName())
                .build();
    }
}

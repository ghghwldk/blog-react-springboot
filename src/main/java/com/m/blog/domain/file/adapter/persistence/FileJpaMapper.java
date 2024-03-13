package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;

public class FileJpaMapper {
    public static File toDomain(FileEntity fileEntity){
        return File.builder()
                .fileName(fileEntity.getFileName())
                .originalFileName(fileEntity.getOriginalFileName())
                .filePath(fileEntity.getFilePath())
                .build();
    }

    public static FileEntity toEntity(File file){
        return FileEntity.builder()
                .fileName(file.getFileName())
                .originalFileName(file.getOriginalFileName())
                .filePath(file.getFilePath())
                .build();
    }
}

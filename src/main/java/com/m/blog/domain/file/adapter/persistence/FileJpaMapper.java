package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Mapper;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;

@Mapper
public class FileJpaMapper {
    public static File toDomain(FileEntity fileEntity){
        return File.builder()
                .fileName(fileEntity.getFileName())
                .originalFileName(fileEntity.getOriginalFileName())
                .filePath(fileEntity.getFilePath())
                .build();
    }
}

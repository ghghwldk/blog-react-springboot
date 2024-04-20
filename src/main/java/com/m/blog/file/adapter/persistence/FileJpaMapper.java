package com.m.blog.file.adapter.persistence;

import com.m.blog.file.application.domain.BaseFile;
import com.m.blog.file.application.domain.File;
import com.m.blog.file.infrastructure.repository.FileEntity;

class FileJpaMapper {
    public static File toDomain(FileEntity fileEntity){
        return File.builder()
                .fileId(BaseFile.FileId.builder()
                        .value(fileEntity.getId())
                        .build())
                .originalFileName(fileEntity.getOriginalFileName())
                .directoryName(fileEntity.getFilePath())
                .build();
    }

    public static FileEntity toEntity(File file){
        return FileEntity.builder()
                .id(file.getFileId().getValue())
                .originalFileName(file.getOriginalFileName())
                .filePath(file.getDirectoryName())
                .build();
    }
}

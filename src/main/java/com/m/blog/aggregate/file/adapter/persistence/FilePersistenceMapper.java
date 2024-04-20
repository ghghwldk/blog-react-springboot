package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.common.Mapper;
import com.m.blog.aggregate.file.application.domain.BaseFile;
import com.m.blog.aggregate.file.application.domain.File;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {
    public static File toDomain(FileEntity fileEntity){
        return File.builder()
                .fileId(BaseFile.FileId.builder()
                        .value(fileEntity.getId())
                        .build())
                .originalFileName(fileEntity.getOriginalFileName())
                .directoryName(fileEntity.getFilePath())
                .build();
    }

    public static FileEntity of(UploadedFile uploadedFile){
        return FileEntity.builder()
                .id(uploadedFile.getFileId().getValue())
                .postingId(uploadedFile
                        .getPostingId()
                        .getValue())
                .originalFileName(uploadedFile.getOriginalFileName())
                .filePath(uploadedFile.getDirectoryName())
                .build();
    }
}

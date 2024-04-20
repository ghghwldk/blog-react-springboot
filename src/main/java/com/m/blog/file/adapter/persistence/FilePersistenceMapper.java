package com.m.blog.file.adapter.persistence;

import com.m.blog.common.Mapper;
import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {
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

package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {

    public static FileEntity of(File_ file){
        return FileEntity.builder()
                .id(file.getFileId().getValue())
                .postingId(file
                        .getPostingId()
                        .getValue())
                .originalFileName(file.getOriginalFileName())
                .filePath(file.getDirectoryName())
                .build();
    }

    public static File_ of(File_ condition, FileEntity entity){
        return condition.setAfterRetrievedUsingDownloadCondition(
                entity.getOriginalFileName(),
                entity.getFilePath(),
                entity.getPostingId()
        );
    }
}

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

    public static File_ of(File_.FileId condition, FileEntity e){
        return File_
                .setAfterRetrievedUsingDownloadCondition(condition.getValue(), e.getOriginalFileName(), e.getFilePath(), e.getPostingId());
    }
}

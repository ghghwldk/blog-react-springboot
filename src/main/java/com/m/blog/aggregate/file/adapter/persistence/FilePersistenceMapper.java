package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.BaseFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {
    public static BlogFile toDomain(FileEntity fileEntity){
        return BlogFile.builder()
                .fileId(BaseFile.FileId.builder()
                        .value(fileEntity.getId())
                        .build())
                .originalFileName(fileEntity.getOriginalFileName())
                .directoryName(fileEntity.getFilePath())
                .build();
    }

    public static FileEntity of(BlogFile blogFile){
        return FileEntity.builder()
                .id(blogFile.getFileId().getValue())
                .postingId(blogFile
                        .getPostingId()
                        .getValue())
                .originalFileName(blogFile.getOriginalFileName())
                .filePath(blogFile.getDirectoryName())
                .build();
    }
}

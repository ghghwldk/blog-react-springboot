package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {
    public static BlogFile toDomain(FileEntity entity){
        return BlogFile.withoutData(new BlogFile.FileId(entity.getId()),
                entity.getOriginalFileName(), entity.getFilePath(), new Posting.PostingId(entity.getPostingId()));
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

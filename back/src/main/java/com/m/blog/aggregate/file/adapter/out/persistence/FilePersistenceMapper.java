package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;

@Mapper
class FilePersistenceMapper {
    public static File_ toDomain(FileEntity entity){
        return File_.withoutData(new File_.FileId(entity.getId()),
                entity.getOriginalFileName(), entity.getFilePath(), new Posting.PostingId(entity.getPostingId()));
    }

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
}

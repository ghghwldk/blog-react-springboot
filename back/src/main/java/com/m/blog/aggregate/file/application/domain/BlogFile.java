package com.m.blog.aggregate.file.application.domain;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Root
@Domain
public class BlogFile extends BaseFile{
    private final byte[] data;

    public BlogFile(String originalFileName, String directoryName, byte[] data, Posting.PostingId postingId){
        this.originalFileName = originalFileName;
        this.fileId = FileId.builder()
                .value(SnowflakeIdGenerator.generateId() + getExtension())
                .build();
        this.directoryName = directoryName;
        this.data = data;
        this.postingId = postingId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class DownloadTrialCondition {
        private BlogFile.FileId fileId;
    }
}
package com.m.blog.file.application.domain;

import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import lombok.*;

@Getter
public class UploadedFile extends BaseFile{
    private final byte[] data;

    public UploadedFile(String originalFileName, String directoryName, byte[] data, Posting.PostingId postingId){
        this.originalFileName = originalFileName;
        this.fileId = FileId.builder()
                .value(SnowflakeIdGenerator.generateId() + getExtension())
                .build();
        this.directoryName = directoryName;
        this.data = data;
        this.postingId = postingId;
    }
}


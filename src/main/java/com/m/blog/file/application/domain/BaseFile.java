package com.m.blog.file.application.domain;

import com.m.blog.boardCollection.application.domain.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseFile {
    private static final String downloadPrefix = "/file/download/";

    protected FileId fileId;
    protected String originalFileName;
    protected String directoryName;

    protected Posting.PostingId postingId;

    protected String getExtension(){
        if(originalFileName == null){
            throw new RuntimeException("originalFileName can't be null.");
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }


    public String getFileKey(){
        return directoryName + "/" + fileId.getValue();
    }

    public String getDownloadUrl(){
        return downloadPrefix + fileId.getValue();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FileId {
        private String value;
    }
}

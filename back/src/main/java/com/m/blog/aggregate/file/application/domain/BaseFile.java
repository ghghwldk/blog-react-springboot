package com.m.blog.aggregate.file.application.domain;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.exception.NotValidValueException;
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
            throw new NotValidValueException();
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

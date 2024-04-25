package com.m.blog.aggregate.file.application.domain;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import com.m.blog.global.exception.CustomIllegalArgumentException;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Getter
@Root
@Domain
public class BlogFile{
    private static final String downloadPrefix = "/file/download/";

    protected FileId fileId;
    protected String originalFileName;
    protected String directoryName;
    protected Posting.PostingId postingId;
    private byte[] data;

    public static BlogFile withoutData(FileId fileId, String originalFileName, String directoryName, Posting.PostingId postingId){
        return new BlogFile(fileId, originalFileName, directoryName, postingId, null);
    }

    private BlogFile(FileId fileId){
        this.fileId = fileId;
    }

    public static BlogFile withDownloadCondition(String fileId){
        return new BlogFile(new FileId(fileId));
    }

    public static BlogFile withSnowflakeId(String originalFileName, String directoryName, String postingId, byte[] data){
        return new BlogFile(new FileId(SnowflakeIdGenerator.generateId() + getExtension(originalFileName)),
                originalFileName, directoryName, new Posting.PostingId(postingId), data);
    }

    private static String getExtension(String originalFileName){
        if(originalFileName == null){
            throw new CustomIllegalArgumentException();
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public BlogFile addData(byte[] data){
        this.data = data;

        return this;
    }


    public static String getFileKey(String directoryName, String fileId){
        return directoryName + "/" + fileId;
    }

    public String getFileKey(){
        return directoryName + "/" + fileId.getValue();
    }

    public String getDownloadUrl(){
        return downloadPrefix + fileId.getValue();
    }

    @Getter
    @AllArgsConstructor
    public static class FileId {
        private String value;
    }


}
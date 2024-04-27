package com.m.blog.aggregate.file.application.domain;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import com.m.blog.global.exception.CustomIllegalArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Root
@Domain
public class File_ {
    private static final String downloadPrefix = "/file/download/";

    protected FileId fileId;
    protected String originalFileName;
    protected String directoryName;
    protected Posting.PostingId postingId;
    private byte[] data;

    public static File_ withoutData(FileId fileId, String originalFileName, String directoryName, Posting.PostingId postingId){
        return new File_(fileId, originalFileName, directoryName, postingId, null);
    }

    private File_(FileId fileId){
        this.fileId = fileId;
    }

    public static File_ withDownloadCondition(String fileId){
        return new File_(new FileId(fileId));
    }

    public static File_ withSnowflakeId(String originalFileName, String directoryName, String postingId, byte[] data){
        return new File_(new FileId(SnowflakeIdGenerator.generateId() + getExtension(originalFileName)),
                originalFileName, directoryName, new Posting.PostingId(postingId), data);
    }

    private static String getExtension(String originalFileName){
        if(originalFileName == null){
            throw new CustomIllegalArgumentException();
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public File_ addData(byte[] data){
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
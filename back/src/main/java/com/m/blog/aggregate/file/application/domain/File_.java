package com.m.blog.aggregate.file.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import com.m.blog.global.exception.CustomIllegalArgumentException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Root
@Domain
public class File_ {
    private static final String downloadPrefix = "/file/download/";

    @Getter private FileId fileId;
    @Getter private String originalFileName;
    @Getter private String directoryName;
    @Getter private Posting.PostingId postingId;
    @Nullable private byte[] data = null;

    @Builder(access = AccessLevel.PRIVATE)
    private File_(String fileId, String originalFileName, String directoryName, String postingId, byte[] data) {
        this.fileId = new FileId(fileId);
        this.originalFileName = originalFileName;
        this.directoryName = directoryName;
        this.postingId = new Posting.PostingId(postingId);
        this.data = data;
    }

    public static String getDownloadPrefix(){return downloadPrefix;}

    public static FileId getDownloadCondition(String fileId){
        return new FileId(fileId);
    }

    public static File_ setAfterRetrievedUsingDownloadCondition
            (String fileId, String originalFileName, String directoryName, String postingId){
        return File_.builder()
                .fileId(fileId)
                .originalFileName(originalFileName)
                .directoryName(directoryName)
                .postingId(postingId)
                .build();
    }

    public File_ setDataAfterDownload(byte[] data){
        this.data = data;

        return this;
    }

    public static List<FileId> getDeleteTargets(List<FileId> existings, List<FileId> whole){
        List<String> existingIds = existings.stream()
                .map(File_.FileId::getValue)
                .collect(Collectors.toList());

        List<File_.FileId> deleteTargets = whole.stream()
                .map(FileId::getValue)
                .filter(fileId -> !existingIds.contains(fileId))
                .map(File_.FileId::new)
                .collect(Collectors.toList());

        return deleteTargets;
    }

    public static FileId getByPostingId(String value){
        return new FileId(value);
    }

    public Optional<byte[]> getDownloadData(){
        return Optional.ofNullable(this.data);
    }

    public static File_ withoutId(String originalFileName, String directoryName, String postingId, byte[] data){
        return File_.builder()
                .originalFileName(originalFileName)
                .directoryName(directoryName)
                .postingId(postingId)
                .data(data)
                .build();
    }

    private static String getExtension(String originalFileName){
        if(originalFileName == null){
            throw new CustomIllegalArgumentException();
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public File_ setSnowflakeIdDuringUpload(){
        this.fileId =
                new FileId(SnowflakeIdGenerator.generateId() + getExtension(originalFileName));

        return this;
    }

    public Optional<byte[]> getUploadData(){
        return Optional.ofNullable(this.data);
    }

    public static String getPathName(String directoryName, String fileId){
        return directoryName + "/" + fileId;
    }

    public String getPathName(){
        return this.getPathName(directoryName, fileId.getValue());
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
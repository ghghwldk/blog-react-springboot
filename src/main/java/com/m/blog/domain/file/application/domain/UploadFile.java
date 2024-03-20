package com.m.blog.domain.file.application.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile extends BaseFile{
    private byte[] data;

    public String getDownloadUrl(){
        return "/file/download/"+ assignedFileName;
    }

    public String getFileKey(String directoryName){
        return directoryName + "/" + assignedFileName;
    }

    private static String getExtension(String originalFileName){
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public UploadFile(String originalFileName, String directoryName, byte[] data){
        this.originalFileName = originalFileName;
        this.assignedFileName = UUID.randomUUID() + getExtension(this.originalFileName);
        this.directoryName = directoryName;
        this.data = data;
    }
}


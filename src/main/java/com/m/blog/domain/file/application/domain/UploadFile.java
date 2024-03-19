package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadFile {
    private String assignedFileName;
    private String originalFileName;
    private String directoryName;

    private String extension;
    private byte[] data;

    public String getDownloadUrl(){
        return "/file/download/"+ assignedFileName;
    }

    public String getFileKey(String directoryName){
        return directoryName + "/" + assignedFileName;
    }
}


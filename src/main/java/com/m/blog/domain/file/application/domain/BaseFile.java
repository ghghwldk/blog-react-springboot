package com.m.blog.domain.file.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseFile {
    private static final String downloadPrefix = "/file/download/";

    protected String assignedFileName;
    protected String originalFileName;
    protected String directoryName;

    protected String getExtension(){
        if(originalFileName == null){
            throw new RuntimeException("originalFileName can't be null.");
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }


    public String getFileKey(){
        return directoryName + "/" + assignedFileName;
    }

    public String getDownloadUrl(){
        return downloadPrefix + assignedFileName;
    }
}

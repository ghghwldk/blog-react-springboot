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

    public UploadFile(String originalFileName, String directoryName, byte[] data){
        this.originalFileName = originalFileName;
        this.assignedFileName = UUID.randomUUID() + getExtension();
        this.directoryName = directoryName;
        this.data = data;
    }
}


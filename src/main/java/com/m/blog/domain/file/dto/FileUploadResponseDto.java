package com.m.blog.domain.file.dto;

import com.google.gson.JsonObject;
import com.m.blog.domain.file.vo.FileVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponseDto {
    private String originalFileName;
    private String fileName;
    private String url;
    private String downloadUrl;

    public static FileUploadResponseDto of(FileVo vo){
        final String url = "/file/download/"+ vo.getSavedFileName();

        return FileUploadResponseDto.builder()
                .originalFileName(vo.getOriginalFileName())
                .fileName(vo.getSavedFileName())
                .url(url)
                .downloadUrl(url)
                .build();
    }


}
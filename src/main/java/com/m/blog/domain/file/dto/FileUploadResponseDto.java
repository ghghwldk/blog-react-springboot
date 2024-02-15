package com.m.blog.domain.file.dto;

import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponseDto {
    private String originalFileName;
    private String fileName;
    private String url;
    private String downloadUrl;

    public static FileUploadResponseDto of(UploadFileVo vo){
        final String url = "/file/download/"+ vo.getSavedFileName();

        return FileUploadResponseDto.builder()
                .originalFileName(vo.getOriginalFileName())
                .fileName(vo.getSavedFileName())
                .url(url)
                .downloadUrl(url)
                .build();
    }


}
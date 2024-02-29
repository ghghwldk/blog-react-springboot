package com.m.blog.domain.file.adapter.out;

import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponse {
    private String originalFileName;
    private String fileName;
    private String url;
    private String downloadUrl;

    public static FileUploadResponse of(UploadFileVo vo){
        final String url = "/file/download/"+ vo.getSavedFileName();

        return FileUploadResponse.builder()
                .originalFileName(vo.getOriginalFileName())
                .fileName(vo.getSavedFileName())
                .url(url)
                .downloadUrl(url)
                .build();
    }


}
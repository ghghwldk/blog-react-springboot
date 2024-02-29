package com.m.blog.domain.file.vo;

import com.m.blog.domain.file.dto.FileDownloadRequest;
import com.m.blog.domain.file.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DownloadFileVo {
    private String path;
    private String key;
    private String originalName;

    public static DownloadFileVo of(FileEntity file, FileDownloadRequest requestDto){
        return DownloadFileVo.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + requestDto.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }
}


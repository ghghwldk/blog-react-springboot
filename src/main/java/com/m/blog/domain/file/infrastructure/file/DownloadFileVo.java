package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
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


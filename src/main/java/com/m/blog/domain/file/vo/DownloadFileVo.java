package com.m.blog.domain.file.vo;

import com.m.blog.domain.file.dto.FileDownloadRequestDto;
import com.m.blog.domain.file.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class DownloadFileVo {
    private String path;
    private String key;
    private String originalName;

    public static DownloadFileVo of(File file, FileDownloadRequestDto requestDto){
        return DownloadFileVo.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + requestDto.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }
}


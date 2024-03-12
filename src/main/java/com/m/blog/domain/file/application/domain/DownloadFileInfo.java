package com.m.blog.domain.file.application.domain;

import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@AllArgsConstructor
public class DownloadFileInfo {
    private String path;
    private String key;
    private String originalName;


    public String getHeaderValues() throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    public static DownloadFileInfo of(File file, DownloadCondition condition){
        return DownloadFileInfo.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + condition.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }

}


package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@AllArgsConstructor
public class DownloadFile {
    private String path;
    private String key;
    private String originalName;


    public String getHeaderValues() throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    public static DownloadFile of(File file, DownloadCondition condition){
        return DownloadFile.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + condition.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }
}


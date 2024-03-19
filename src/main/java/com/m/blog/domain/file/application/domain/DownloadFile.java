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



    @Data
    @Builder
    @AllArgsConstructor
    public static class TrialCondition {
        private String fileName;
    }
}


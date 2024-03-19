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
    private String assignedFileName;
    private String originalFileName;
    private String directoryName;

    public static DownloadFile of(File file){
        return DownloadFile.builder()
                .directoryName(file.getDirectoryName())
                .assignedFileName(file.getAssignedFileName())
                .originalFileName(file.getOriginalFileName())
                .build();
    }

    public String getFileKey(){
        return directoryName + "/" + assignedFileName;
    }

    public String getHeaderValues() throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
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


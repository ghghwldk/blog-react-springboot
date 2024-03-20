package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Builder
@Data
public class File {
    String assignedFileName;
    String originalFileName;
    String directoryName;

    public static File of(File file){
        return File.builder()
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
        private String assignedFileName;
    }
}
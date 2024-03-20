package com.m.blog.domain.file.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseFile {
    protected String assignedFileName;
    protected String originalFileName;
    protected String directoryName;

    public String getHeaderValues() throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }
}
